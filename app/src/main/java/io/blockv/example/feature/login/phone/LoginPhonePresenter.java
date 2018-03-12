package io.blockv.example.feature.login.phone;

import android.view.View;

/**
 * LoginPhonePresenter handles business logic for LoginPhoneFragment
 * @see LoginPhoneFragment
 */
public interface LoginPhonePresenter
{
  /**
   * Attempt to login via phone number
   * @param view
   * @param user is the user's phone number
   * @param password is the user's password
   */
  void onLoginButtonClick(View view, String user, String password);

  /**
   * Reset the user's password causing a one time password to be sent by the server
   * @param view
   * @param user is the user's phone number
   */
  void onSendOtpButtonClick(View view, String user);

  void onDestroy();
}
