package io.blockv.example.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseActivity;

/**
 * The VatomMetaActivity demonstrates fetching a vatom by id
 *
 * @see VatomMetaPresenterImpl
 */
public class VatomMetaActivity extends BaseActivity {

  public static Intent getIntent(Context context, String vatomId) {
    Intent intent = new Intent(context, VatomMetaActivity.class);
    intent.putExtra(Extras.VATOM_ID, vatomId);
    return intent;
  }

  VatomMetaPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vatom_meta);
    VatomMetaScreen screen = new VatomMetaScreenImpl(this);
    presenter = new VatomMetaPresenterImpl(screen);
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
