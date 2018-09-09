package io.blockv.example.feature.activated;

import io.blockv.core.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.details.VatomMetaActivity;

public class ActivatedScreenImpl extends BaseScreen implements ActivatedScreen {

  final android.support.v7.widget.Toolbar toolbar;

  public ActivatedScreenImpl(ActivatedActivity activity) {
    super(activity);
    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

  }

  @Override
  public void registerEvents(ActivatedPresenter presenter) {

  }

  @Override
  public void setVatom(Vatom vatom) {

  }

  @Override
  public void startVatomDetailsActivity(String vatomId) {
    activity.startActivity(VatomMetaActivity.getIntent(activity, vatomId));
  }
}

