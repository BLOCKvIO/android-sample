package io.blockv.example.feature.login.phone;

import android.view.View;
import io.blockv.core.client.manager.UserManager;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginPhonePresenterImpl extends BasePresenter implements LoginPhonePresenter {

  private final LoginPhoneScreen screen;

  public LoginPhonePresenterImpl(LoginPhoneScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onLoginButtonClick(View view, String token, String password) {
    ///attempt to login using the provided phone number address and password
    collect(
      userManager.login(token, UserManager.TokenType.PHONE_NUMBER, password)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val->screen.showDialog(getString(R.string.login_page_dialog_logging_in)))
        .doFinally(screen::hideDialog)
        .subscribe(user -> {
            //on success you will receive a user model containing the user's details
            screen.startInventoryActivity();
          },
          throwable -> {
            screen.showToast(throwable.getMessage());
          }));
  }

  @Override
  public void onSendOtpButtonClick(View view, String user) {
    //attempt to reset the users password, on success an OTP to be sent to the provided phone number
    collect(
      userManager.resetToken(user, UserManager.TokenType.PHONE_NUMBER)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val-> screen.showDialog(getString(R.string.login_page_dialog_sending_otp)))
        .doFinally(screen::hideDialog)
      .subscribe(() -> {
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
