package io.blockv.example.feature.inventory;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/**
 * InventoryPresenter handles business logic for InventoryActivity
 * @see InventoryActivity
 */

public interface InventoryPresenter {

  void onCreate(Bundle savedInstanceState);

  void onResume();

  void onPause();

  void onOptionsItemSelected(MenuItem menuItem);

  void onSwipeRefresh();

  void onItemClicked(View view, String vatomId);
}
