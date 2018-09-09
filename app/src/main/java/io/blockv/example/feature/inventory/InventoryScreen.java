package io.blockv.example.feature.inventory;

import io.blockv.common.model.Vatom;

import java.util.List;

/**
 * InventoryScreen handles screen layout and navigation for InventoryActivity
 *
 * @see InventoryActivity
 */
public interface InventoryScreen {

  void registerEvents(InventoryPresenter presenter);

  void setVatoms(List<Vatom> vatoms);

  void showRefreshing(boolean show);

  void finish();

  void startProfileActivity();

  void startActivatedActivity(String vatomId);

}
