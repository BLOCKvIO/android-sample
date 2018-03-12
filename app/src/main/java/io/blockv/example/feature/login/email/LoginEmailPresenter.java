package io.blockv.example.feature.login.email;

import android.view.View;

/**
 * LoginEmailPresenter handles business logic for LoginEmailFragment
 * @see LoginEmailFragment
 */
public interface LoginEmailPresenter
{

  /**
   * Attempt to login via email
   * @param view
   * @param user is the user's email address
   * @param password is the user's password
   */
  void onLoginButtonClick(View view, String user, String password);

  /**
   * Reset the user's password causing a one time password to be sent by the server
   * @param view
   * @param user is the user's email address
   */
  void onSendOtpButtonClick(View view, String user);

  void onDestroy();
}
