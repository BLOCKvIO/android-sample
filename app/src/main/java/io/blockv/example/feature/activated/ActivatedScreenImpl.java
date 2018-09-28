package io.blockv.example.feature.activated;

import android.view.LayoutInflater;
import android.widget.TextView;
import io.blockv.common.model.Vatom;
import io.blockv.common.util.Cancellable;
import io.blockv.common.util.CompositeCancellable;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.details.VatomMetaActivity;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;
import timber.log.Timber;

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
    name.setText(vatom.getProperty().getTitle());
    description.setText(vatom.getProperty().getDescription());

    CompositeCancellable cancellable = new CompositeCancellable();
    cancellable.add(faceManager
      .load(vatom)
      .setErrorView(LayoutInflater.from(activity).inflate(R.layout.view_basic_error, icon, false))
      .into(icon)
      .call(faceView -> {
        Timber.e(faceView.toString());
      }, throwable -> {
        Timber.e(throwable.getMessage());
      }));
    cancellable.add(faceManager
      .load(vatom)
      .setEmbeddedProcedure(FaceManager.EmbeddedProcedure.ENGAGED)
      .into(engaged)
      .call(faceView -> {
        Timber.e(faceView.toString());
      }, throwable -> {
        Timber.e(throwable.getMessage());
      }));
    cancellable.add(faceManager
      .load(vatom)
      .setEmbeddedProcedure(FaceManager.EmbeddedProcedure.CARD)
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

