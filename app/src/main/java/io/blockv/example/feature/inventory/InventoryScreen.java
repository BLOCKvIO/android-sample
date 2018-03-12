package io.blockv.example.feature.inventory;

import io.blockv.core.model.Vatom;
import io.blockv.example.feature.login.LoginActivity;

import java.util.List;


/**
 * InventoryScreen handles screen layout and navigation for InventoryActivity
 * @see InventoryActivity
 */
public interface InventoryScreen
{
  void registerEvents(InventoryPresenter presenter);

  void setVatoms(List<Vatom> vatoms);

  void finish();

  void startProfileActivity();

}
