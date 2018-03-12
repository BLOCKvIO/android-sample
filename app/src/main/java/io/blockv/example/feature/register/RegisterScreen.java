package io.blockv.example.feature.register;

/**
 * LoginScreen handles screen layout and navigation for RegisterActivity
 * @see RegisterActivity
 */

public interface RegisterScreen {

  void registerEvents(RegisterPresenter presenter);

  void showDialog(String text);

  void hideDialog();

  void showToast(String text);

  void startVerifyActivity(String phoneNumber,String email);

  void finish();
}
