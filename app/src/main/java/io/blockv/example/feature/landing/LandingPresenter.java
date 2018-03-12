package io.blockv.example.feature.landing;

import android.view.View;

/**
 * LandingPresenter handles business logic for LandingActivity
 * @see LandingActivity
 */
public interface LandingPresenter
{
  void onCreate();

  void onDestroy();

  void onLoginButtonClick(View view);

  void onRegisterButtonClick(View view);
  
}
