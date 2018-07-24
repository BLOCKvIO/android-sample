package io.blockv.example.feature.inventory;

import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.core.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

public class InventoryPresenterImpl extends BasePresenter implements InventoryPresenter {


  private final InventoryScreen screen;

  public InventoryPresenterImpl(InventoryScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
  }

  public void onResume() {
    refresh();
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

  @Override
  public void onSwipeRefresh() {
    refresh();
  }

  private void refresh() {
    dispose();
    ///load the user's vAtoms from root inventory
    collect(
      vatomManager
        .getInventory(".", 1, 100)//inventory id "." is root
        .call(group -> {
            if (group != null) {
              //filter out vAtoms
              List<Vatom> out = new ArrayList<>();
              for (Vatom vatom : group.getVatoms()) {
                if (!vatom.getProperty().isDropped()//Filter out dropped vAtoms
                  && !vatom.getProperty().getTemplateId().endsWith("::vAtom::Avatar")//filter out avatar vAtoms
                  && !vatom.getProperty().getTemplateId().endsWith("::vAtom::CoinWallet")//filter out wallet vAtoms
                  ) {
                  out.add(vatom);
                }
              }
              screen.setVatoms(out);
            }
            screen.showRefreshing(false);
          },
          throwable -> Timber.e(throwable.getMessage())
        ));
  }
}
