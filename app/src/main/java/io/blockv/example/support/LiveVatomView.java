package io.blockv.example.support;

import android.support.annotation.NonNull;
import android.view.View;
import io.blockv.common.model.Vatom;
import io.blockv.core.client.manager.EventManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

import java.util.concurrent.Semaphore;

public class LiveVatomView {
  private Vatom vatom;
  final VatomView vatomView;
  final VatomManager vatomManager;
  final FaceManager faceManager;
  final EventManager eventManager;
  private View loaderView;
  private View errorView;
  private long loaderDelay = 0;
  private FaceManager.FaceSelectionProcedure selectionProcedure;

  public LiveVatomView(
    @NonNull VatomView vatomView,
    @NonNull VatomManager vatomManager,
    @NonNull FaceManager faceManager,
    @NonNull EventManager eventManager) {

    this.vatomView = vatomView;
    this.vatomManager = vatomManager;
    this.faceManager = faceManager;
    this.eventManager = eventManager;
  }

  public Vatom getVatom() {
    return vatom;
  }

  public View getLoaderView() {
    return loaderView;
  }

  public LiveVatomView setLoaderView(View loaderView) {
    this.loaderView = loaderView;
    return this;
  }

  public View getErrorView() {
    return errorView;
  }

  public LiveVatomView setErrorView(View errorView) {
    this.errorView = errorView;
    return this;
  }

  public long getLoaderDelay() {
    return loaderDelay;
  }

  public LiveVatomView setLoaderDelay(long loaderDelay) {
    this.loaderDelay = loaderDelay;
    return this;
  }

  public FaceManager.FaceSelectionProcedure getSelectionProcedure() {
    return selectionProcedure;
  }

  public LiveVatomView setSelectionProcedure(FaceManager.FaceSelectionProcedure selectionProcedure) {
    this.selectionProcedure = selectionProcedure;
    return this;
  }

  public Flowable<Vatom> load(Vatom vatom) {
    this.vatom = vatom;
    Semaphore updateLock = new Semaphore(1);
    return faceManager
      .load(vatom)
      .setFaceSelectionProcedure(selectionProcedure)
      .setLoaderView(loaderView)
      .setErrorView(errorView)
      .setLoaderDelay(loaderDelay)
      .into(vatomView)
      .toFlowable()
      .flatMap(view -> eventManager.getVatomStateEvents())
      .filter(event ->
        event.getPayload() != null
          && event.getPayload().getVatomId().equals(this.vatom.getId()))
      .observeOn(Schedulers.computation())
      .flatMap(event -> {
        updateLock.acquire();
        return vatomManager
          .updateVatom(this.vatom, event.getPayload())
          .map(updated -> {
            this.vatom = updated;
            return updated;
          })
          .doFinally(updateLock::release)
          .toFlowable();
      })
      .observeOn(AndroidSchedulers.mainThread())
      .flatMap(update -> faceManager
        .load(update)
        .setFaceSelectionProcedure(selectionProcedure)
        .setLoaderView(loaderView)
        .setLoaderView(errorView)
        .setLoaderDelay(loaderDelay)
        .into(vatomView)
        .map(vatomView -> update)
        .toFlowable());
  }
}
