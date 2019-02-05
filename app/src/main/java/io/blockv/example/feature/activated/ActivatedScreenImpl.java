package io.blockv.example.feature.activated;

import android.view.LayoutInflater;
import android.widget.TextView;
import io.blockv.common.model.Face;
import io.blockv.common.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.details.VatomMetaActivity;
import io.blockv.example.support.LiveVatomView;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;
import io.reactivex.Flowable;

import java.util.List;

public class ActivatedScreenImpl extends BaseScreen implements ActivatedScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final TextView name;
  final TextView description;
  final VatomView icon;
  final VatomView engaged;
  final VatomView card;
  final LiveVatomView liveIcon;
  final LiveVatomView liveEngaged;
  final LiveVatomView liveCard;

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
    LayoutInflater inflater = LayoutInflater.from(activity);

    liveIcon = new LiveVatomView(
      icon,
      vatomManager,
      faceManager,
      eventManager)
      .setSelectionProcedure(FaceManager.EmbeddedProcedure.ICON.getProcedure());

    liveEngaged = new LiveVatomView(
      engaged,
      vatomManager,
      faceManager,
      eventManager)
      .setSelectionProcedure(FaceManager.EmbeddedProcedure.ENGAGED.getProcedure())
      .setLoaderView(inflater.inflate(R.layout.view_custom_loader, engaged, false))
      .setErrorView(inflater.inflate(R.layout.view_custom_error, engaged, false));

    liveCard = new LiveVatomView(
      card,
      vatomManager,
      faceManager,
      eventManager)
      .setSelectionProcedure(FaceManager.EmbeddedProcedure.CARD.getProcedure());
  }

  @Override
  public void registerEvents(ActivatedPresenter presenter) {

  }

  @Override
  public Flowable<Vatom> setVatom(Vatom vatom) {

    name.setText(vatom.getProperty().getTitle());
    description.setText(vatom.getProperty().getDescription());

    return liveIcon.load(vatom)
      .onErrorReturn(throwable -> vatom)
      .mergeWith(liveEngaged.load(vatom)
        .onErrorReturn(throwable -> vatom))
      .mergeWith(liveCard.load(vatom)
        .onErrorReturn(throwable -> vatom)
      );
  }

  @Override
  public void startVatomDetailsActivity(String vatomId) {
    activity.startActivity(VatomMetaActivity.getIntent(activity, vatomId));
  }
}

