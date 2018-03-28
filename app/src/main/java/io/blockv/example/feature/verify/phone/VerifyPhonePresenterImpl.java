package io.blockv.example.feature.verify.phone;

import android.os.Bundle;
import android.view.View;
import io.blockv.core.client.manager.UserManager;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;


public class VerifyPhonePresenterImpl extends BasePresenter implements VerifyPhonePresenter {

  private final VerifyPhoneScreen screen;


  public VerifyPhonePresenterImpl(VerifyPhoneScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreateView(Bundle bundle) {
    screen.setPhoneNumber(bundle.getString(Extras.PHONE_NUMBER));
  }

  @Override
  public void onVerifyButtonClicked(View view,String phoneNumber,String code) {

    screen.showDialog(getString(R.string.verify_page_verifying));
    //attempt to verify the user's phone number
    collect(
      userManager
        .verifyUserToken(phoneNumber, UserManager.TokenType.PHONE_NUMBER, code)
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
  public void onResendOtpButtonClicked(View view,String phoneNumber) {
    screen.showDialog(getString(R.string.verify_page_sending));
    //request a new verification code
    collect(userManager.resendVerification(phoneNumber, UserManager.TokenType.PHONE_NUMBER)
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
