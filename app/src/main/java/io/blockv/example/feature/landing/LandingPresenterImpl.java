package io.blockv.example.feature.landing;

import android.view.View;
import io.blockv.example.feature.BasePresenter;
import timber.log.Timber;

public class LandingPresenterImpl extends BasePresenter implements LandingPresenter {

  private final LandingScreen screen;

  public LandingPresenterImpl(LandingScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate() {
    //if there is a logged in user navigate to inventory
    if (userManager.isLoggedIn()) {
      screen.startInventoryActivity();
    }
  }

  public void onDestroy() {
    dispose();
  }

  @Override
  public void onLoginButtonClick(View view) {

    screen.startLoginActivity();
  }

  @Override
  public void onRegisterButtonClick(View view) {
    screen.startRegisterActivity();
  }


}
