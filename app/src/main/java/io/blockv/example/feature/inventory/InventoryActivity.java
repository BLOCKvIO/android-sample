package io.blockv.example.feature.inventory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.feature.BaseActivity;

/**
 * The InventoryActivity demonstrates how to fetch the current users vatoms
 *
 * @see InventoryPresenterImpl
 */
public class InventoryActivity extends BaseActivity {

  public static Intent getIntent(Context context) {
    return new Intent(context, InventoryActivity.class);
  }

  InventoryPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inventory);
    InventoryScreen screen = new InventoryScreenImpl(this);
    presenter = new InventoryPresenterImpl(screen);
    screen.registerEvents(presenter);

    presenter.onCreate(savedInstanceState);
  }

  public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  public void onDestroy() {
    super.onDestroy();
  }

  public void onPause()
  {
    super.onPause();
    presenter.onPause();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    presenter.onOptionsItemSelected(menuItem);
    return super.onOptionsItemSelected(menuItem);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_inventory, menu);
    return super.onCreateOptionsMenu(menu);
  }

}
