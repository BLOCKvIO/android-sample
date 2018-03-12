package io.blockv.example.feature.verify.phone;

import android.os.Bundle;
import android.view.View;

/**
 * VerifyPhonePresenter handles business logic for VerifyPhoneFragment
 * @see VerifyPhoneFragment
 */

public interface VerifyPhonePresenter {

  void onCreateView(Bundle bundle);

  /**
   * Attempts to verify the user's email address
   *
   * @param view
   * @param phone is the user's phone number
   * @param code is the latest verification code sent to the above phone number
   */
  void onVerifyButtonClicked(View view, String phone,String code);

  /**
   * Request a new verification code from the server
   *
   * @param view
   * @param phone is the user's phone number
   */
  void onResendOtpButtonClicked(View view,String phone);

  void onDestroy();
}
