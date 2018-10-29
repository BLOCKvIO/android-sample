package io.blockv.example.support;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import io.blockv.common.model.Vatom;
import io.blockv.common.util.Callable;
import io.blockv.common.util.Cancellable;
import io.blockv.core.client.manager.EventManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.FaceView;
import io.blockv.face.client.VatomView;
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

  public Callable<Vatom> load(Vatom vatom) {
    this.vatom = vatom;

    return faceManager
      .load(vatom)
      .setFaceSelectionProcedure(selectionProcedure)
      .setLoaderView(loaderView)
      .setErrorView(errorView)
      .setLoaderDelay(loaderDelay)
      .into(vatomView)
      .flatMap(view -> {
        Vatom currentVatom = view.getVatom();
        return Callable.Companion.<Vatom>create(emitter -> {
          emitter.onResult(currentVatom);
          Semaphore updateLock = new Semaphore(1);
          Cancellable cancel = eventManager
            .getVatomStateEvents()
            .filter(event -> event.getPayload() != null && event.getPayload().getVatomId().equals(currentVatom.getId()))
            .flatMap(update -> {
              try {
                updateLock.acquire();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return vatomManager.updateVatom(LiveVatomView.this.vatom, update.getPayload());
            })
            .returnOn(Callable.Scheduler.COMP)
            .call(newVatom -> {
              LiveVatomView.this.vatom = newVatom;
              updateLock.release();
              emitter.onResult(newVatom);
            }, throwable -> {
              updateLock.release(1000);
              emitter.onError(throwable);
            });
          emitter.doOnCompletion(() -> {
            cancel.cancel();
            updateLock.release(1000);
            return Unit.INSTANCE;
          });
          return Unit.INSTANCE;
        });
      })
      .flatMap(update ->
      {
        LiveVatomView.this.vatom = update;
        return faceManager
          .load(update)
          .setFaceSelectionProcedure(selectionProcedure)
          .setLoaderView(loaderView)
          .setLoaderView(errorView)
          .setLoaderDelay(loaderDelay)
          .into(vatomView);
      })
      .map(FaceView::getVatom);
  }
}
