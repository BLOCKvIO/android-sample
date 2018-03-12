package io.blockv.example.feature.login;

import android.view.MenuItem;
import io.blockv.example.feature.BasePresenter;

public class LoginPresenterImpl extends BasePresenter implements LoginPresenter {

  private final LoginScreen screen;

  public LoginPresenterImpl(LoginScreen screen) {
    this.screen = screen;
  }


  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      screen.finish();
    }

  }

  @Override
  public void onDestroy() {
    dispose();
  }
}
