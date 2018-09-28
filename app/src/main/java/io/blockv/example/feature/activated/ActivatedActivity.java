package io.blockv.example.feature.activated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseActivity;

/**
 * The ActivatedActivity demonstrates fetching a vatom by id and loading a vatomview.
 *
 * @see ActivatedPresenterImpl
 */
public class ActivatedActivity extends BaseActivity {

  public static Intent getIntent(Context context, String vatomId) {
    Intent intent = new Intent(context, ActivatedActivity.class);
    intent.putExtra(Extras.VATOM_ID, vatomId);
    return intent;
  }

  ActivatedPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vatom_activated);
    ActivatedScreen screen = new ActivatedScreenImpl(this);
    presenter = new ActivatedPresenterImpl(screen);
    screen.registerEvents(presenter);
    presenter.onCreate(getIntent());
  }

  public void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    presenter.onOptionsItemSelected(menuItem);
    return super.onOptionsItemSelected(menuItem);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_activated, menu);
    return super.onCreateOptionsMenu(menu);
  }
}
