package io.blockv.example.feature.login.phone;

import android.view.View;
import io.blockv.core.client.manager.UserManager;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;

public class LoginPhonePresenterImpl extends BasePresenter implements LoginPhonePresenter {

  private final LoginPhoneScreen screen;

  public LoginPhonePresenterImpl(LoginPhoneScreen screen) {
    this.screen = screen;
  }


  @Override
  public void onLoginButtonClick(View view, String token, String password) {
    screen.showDialog(getString(R.string.login_page_dialog_logging_in));
    ///attempt to login using the provided phone number address and password
    collect(userManager.login(token, UserManager.TokenType.PHONE_NUMBER, password)
      .call(user -> {
          //on success you will receive a user model containing the user's details
          screen.hideDialog();
          screen.startInventoryActivity();
        },
        throwable -> {
          screen.hideDialog();
          screen.showToast(throwable.getMessage());
        }));
  }

  @Override
  public void onSendOtpButtonClick(View view, String user) {

    screen.showDialog(getString(R.string.login_page_dialog_sending_otp));
    //attempt to reset the users password, on success an OTP to be sent to the provided phone number
    collect(userManager
      .resetToken(user, UserManager.TokenType.PHONE_NUMBER)
      .call(Void -> {
        screen.hideDialog();
        screen.showToast(getString(R.string.login_page_otp_sent));
      }, throwable -> {
        screen.hideDialog();
        screen.showToast(throwable.getMessage());
      }));
  }

  @Override
  public void onDestroy() {
    dispose();
  }
}
