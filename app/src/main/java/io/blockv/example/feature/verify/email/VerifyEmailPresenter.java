package io.blockv.example.feature.verify.email;

import android.os.Bundle;
import android.view.View;

/**
 * VerifyEmailPresenter handles business logic for VerifyEmailFragment
 * @see VerifyEmailFragment
 */

public interface VerifyEmailPresenter {

  void onCreateView(Bundle bundle);

  /**
   * Attempts to verify the user's email address
   *
   * @param view
   * @param email is the user's email address
   * @param code is the latest verification code sent to the above email address
   */
  void onVerifyButtonClicked(View view,String email, String code);

  /**
   * Request a new verification code from the server
   *
   * @param view
   * @param email is the user's email address
   */
  void onResendOtpButtonClicked(View view,String email);

  void onDestroy();
}
