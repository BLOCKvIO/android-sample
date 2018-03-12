package io.blockv.example.feature.inventory;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import io.blockv.core.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.profile.ProfileActivity;

import java.util.List;


public class InventoryScreenImpl extends BaseScreen implements InventoryScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final RecyclerView list;
  final InventoryAdapter adapter;

  public InventoryScreenImpl(InventoryActivity activity) {
    super(activity);

    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);

    list = activity.findViewById(R.id.list);
    adapter = new InventoryAdapter(vatomManager,picasso);
    list.setLayoutManager(new GridLayoutManager(activity,2));
    list.setAdapter(adapter);
  }

  @Override
  public void registerEvents(InventoryPresenter presenter) {
  }

  @Override
  public void setVatoms(List<Vatom> vatoms) {
    adapter.setItems(vatoms);
  }

  @Override
  public void startProfileActivity() {

    activity.startActivity(ProfileActivity.getIntent(activity));
  }


}

