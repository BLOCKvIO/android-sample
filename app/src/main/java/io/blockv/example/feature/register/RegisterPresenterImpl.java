package io.blockv.example.feature.register;

import android.view.MenuItem;
import android.view.View;
import io.blockv.common.builder.RegistrationBuilder;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RegisterPresenterImpl extends BasePresenter implements RegisterPresenter {

  private final RegisterScreen screen;

  public RegisterPresenterImpl(RegisterScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onRegisterButtonClick(View view,
                                    String firstName,
                                    String lastName,
                                    String password,
                                    String email,
                                    String phoneNumber) {
// register a new user, a valid email or phone number is required
    collect(
      userManager.register(new RegistrationBuilder()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setPassword(password)
        .addEmail(email)
        .addPhoneNumber(phoneNumber.replaceAll("[()\\-\\s]", ""))
        .build())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val-> screen.showDialog(getString(R.string.register_page_dialog_registering)))
        .doFinally(screen::hideDialog)
        .subscribe(user -> {
          //on success you will receive a user model containing the user's details
          screen.showToast(getString(R.string.register_page_success));
          screen.startVerifyActivity(phoneNumber.replaceAll("[()\\-\\s]", ""), email);
        }, throwable -> {
          screen.showToast(throwable.getMessage());
        }));
  }

  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      screen.finish();
    }

  }

  @Override
  public void onDestroy() {
    dispose();
  }
}
