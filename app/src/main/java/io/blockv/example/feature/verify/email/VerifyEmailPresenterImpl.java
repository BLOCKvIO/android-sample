package io.blockv.example.feature.verify.email;

import android.os.Bundle;
import android.view.View;
import io.blockv.core.client.manager.UserManager;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;


public class VerifyEmailPresenterImpl extends BasePresenter implements VerifyEmailPresenter {

  private final VerifyEmailScreen screen;

  public VerifyEmailPresenterImpl(VerifyEmailScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreateView(Bundle bundle) {
    screen.setEmail(bundle.getString(Extras.EMAIL));
  }

  @Override
  public void onVerifyButtonClicked(View view, String email, String code) {

    screen.showDialog(getString(R.string.verify_page_verifying));
    //attempt to verify the user's email address
    collect(
      userManager
        .verifyUserToken(email, UserManager.TokenType.EMAIL, code)
        .call(Void -> {
          screen.showToast(getString(R.string.verify_page_success));
          screen.startInventoryActivity();
          screen.hideDialog();
        }, throwable -> {
          screen.hideDialog();
          screen.showToast(throwable.getMessage());
        }));
  }

  @Override
  public void onResendOtpButtonClicked(View view, String email) {
    screen.showDialog(getString(R.string.verify_page_sending));
    //request a new verification code
    collect(userManager.resendVerification(email, UserManager.TokenType.EMAIL)
      .call(Void -> {
          screen.hideDialog();
          screen.showToast(getString(R.string.verify_page_success));
        },
        throwable -> {
          screen.hideDialog();
          screen.showToast(throwable.getMessage());
        }));
  }

  @Override
  public void onDestroy() {
    dispose();
  }
}
