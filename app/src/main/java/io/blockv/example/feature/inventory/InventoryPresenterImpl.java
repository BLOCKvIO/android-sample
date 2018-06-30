package io.blockv.example.feature.inventory;

import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;
import timber.log.Timber;

public class InventoryPresenterImpl extends BasePresenter implements InventoryPresenter {


  private final InventoryScreen screen;

  public InventoryPresenterImpl(InventoryScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
  }

  public void onResume() {

    ///load the user's vAtoms from root inventory
    collect(vatomManager
      .getInventory(".")//inventory id "." is root
      .call(group -> {
          if (group != null) {
            screen.setVatoms(group.getVatoms());
          }
        },
        throwable -> Timber.e(throwable.getMessage())
      ));
  }

  @Override
  public void onPause() {
    dispose();
  }


  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == R.id.profile) {
      screen.startProfileActivity();
    }

  }
}
