package io.blockv.example.feature.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.feature.BaseActivity;

/**
 * The VatomActivity demonstrates fetching and updating the current logged in user's details,
 *
 * @see ProfilePresenterImpl
 */
public class ProfileActivity extends BaseActivity {

  public static Intent getIntent(Context context) {
    return new Intent(context, ProfileActivity.class);
  }

  ProfilePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ProfileScreen screen = new ProfileScreenImpl(this);
    presenter = new ProfilePresenterImpl(screen,getContentResolver());
    screen.registerEvents(presenter);

    presenter.onCreate(savedInstanceState);
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
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    presenter.onActivityResult(requestCode, resultCode, data);
  }
}
