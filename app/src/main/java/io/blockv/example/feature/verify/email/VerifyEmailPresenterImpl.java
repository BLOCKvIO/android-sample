package io.blockv.example.feature.verify.email;

import android.os.Bundle;
import android.view.View;
import io.blockv.core.client.manager.UserManager;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;


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

    //attempt to verify the user's email address
    collect(
      userManager.verifyUserToken(email, UserManager.TokenType.EMAIL, code)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val -> screen.showDialog(getString(R.string.verify_page_verifying)))
        .doFinally(screen::hideDialog)
        .subscribe(() -> {
          screen.showToast(getString(R.string.verify_page_success));
          screen.startInventoryActivity();
        }, throwable -> {
          screen.showToast(throwable.getMessage());
        }));
  }

  @Override
  public void onResendOtpButtonClicked(View view, String email) {
    //request a new verification code
    collect(
      userManager.resendVerification(email, UserManager.TokenType.EMAIL)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val -> screen.showDialog(getString(R.string.verify_page_verifying)))
        .doFinally(screen::hideDialog)
        .subscribe(() -> {
            screen.showToast(getString(R.string.verify_page_success));
          },
          throwable -> {
            screen.showToast(throwable.getMessage());
          }));
  }

  @Override
  public void onDestroy() {
    dispose();
  }
}
