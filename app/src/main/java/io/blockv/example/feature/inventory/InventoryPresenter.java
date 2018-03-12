package io.blockv.example.feature.inventory;

import android.os.Bundle;
import android.view.MenuItem;

/**
 * InventoryPresenter handles business logic for InventoryActivity
 * @see InventoryActivity
 */

public interface InventoryPresenter {

  void onCreate(Bundle savedInstanceState);

  void onResume();

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);
}
