package io.blockv.example.feature.activated;

import android.view.LayoutInflater;
import android.widget.TextView;
import io.blockv.common.model.Face;
import io.blockv.common.model.Vatom;
import io.blockv.common.util.Cancellable;
import io.blockv.common.util.CompositeCancellable;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.details.VatomMetaActivity;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

public class ActivatedScreenImpl extends BaseScreen implements ActivatedScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final TextView name;
  final TextView description;
  final VatomView icon;
  final VatomView engaged;
  final VatomView card;

  public ActivatedScreenImpl(ActivatedActivity activity) {
    super(activity);
    toolbar = activity.findViewById(R.id.toolbar);
    name = activity.findViewById(R.id.name);
    description = activity.findViewById(R.id.description);
    icon = activity.findViewById(R.id.icon);
    engaged = activity.findViewById(R.id.engaged);
    card = activity.findViewById(R.id.card);

    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

  }

  @Override
  public void registerEvents(ActivatedPresenter presenter) {

  }

  @Override
  public Cancellable setVatom(Vatom vatom) {
    LayoutInflater inflater = LayoutInflater.from(activity);
    name.setText(vatom.getProperty().getTitle());
    description.setText(vatom.getProperty().getDescription());

    CompositeCancellable cancellable = new CompositeCancellable();
    //load vatomview using the default settings.
    cancellable.add(
      faceManager.load(vatom)
        .setEmbeddedProcedure(FaceManager.EmbeddedProcedure.ICON)
        .into(icon)
        .call(faceView -> {
          Timber.e(faceView.toString());
        }, throwable -> {
          Timber.e(throwable.getMessage());
        }));

    cancellable.add(
      faceManager.load(vatom)
        .setEmbeddedProcedure(FaceManager.EmbeddedProcedure.ENGAGED)
        .setLoaderView(inflater.inflate(R.layout.view_custom_loader, engaged, false))
        .setErrorView(inflater.inflate(R.layout.view_custom_error, engaged, false))
        .into(engaged)
        .call(faceView -> {
          Timber.e(faceView.toString());
        }, throwable -> {
          Timber.e(throwable.getMessage());
        }));

    cancellable.add(
      faceManager.load(vatom)
        // .setEmbeddedProcedure(FaceManager.EmbeddedProcedure.CARD)
        .setFaceSelectionProcedure((selectedVatom, displayUrls) -> {
          ArrayList<Face> out = new ArrayList<>();
          List<Face> faces = selectedVatom.getFaces();
          Face selected = null;
          int rating = -1;
          for (Face face : faces) {

            //we only want to display a face in view mode card
            if (!face.getProperty().getViewMode().equalsIgnoreCase("card"))
              continue;

            //only want to select a native face
            if (!face.isNative())
              continue;

            //check that the required faceview is registered
            if (!displayUrls.contains(face.getProperty().getDisplayUrl().toLowerCase()))
              continue;

            int value;
            //Selected a face with platform Android over generic
            if (face.getProperty().getPlatform().equalsIgnoreCase("android")) {
              value = 2;
            } else if (face.getProperty().getPlatform().equalsIgnoreCase("generic")) {
              value = 1;
            } else
              continue;//unsupported platform

            if (value > rating) {
              rating = value;
              selected = face;
            }
          }
          return selected;
        })
        .into(card)
        .call(faceView -> {
          Timber.e(faceView.toString());
        }, throwable -> {
          Timber.e(throwable.getMessage());
        }));
    return cancellable;
  }

  @Override
  public void startVatomDetailsActivity(String vatomId) {
    activity.startActivity(VatomMetaActivity.getIntent(activity, vatomId));
  }
}

