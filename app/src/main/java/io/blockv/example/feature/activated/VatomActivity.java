package io.blockv.example.feature.activated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseActivity;

/**
 * The VatomActivity demonstrates fetching a vatom by id
 *
 * @see VatomPresenterImpl
 */
public class VatomActivity extends BaseActivity {

  public static Intent getIntent(Context context, String vatomId) {
    Intent intent = new Intent(context, VatomActivity.class);
    intent.putExtra(Extras.VATOM_ID, vatomId);
    return intent;
  }

  VatomPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vatom);
    VatomScreen screen = new VatomScreenImpl(this);
    presenter = new VatomPresenterImpl(screen);
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
}
