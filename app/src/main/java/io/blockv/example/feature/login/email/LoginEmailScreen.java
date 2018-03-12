package io.blockv.example.feature.login.email;

/**
 * LoginScreen handles screen layout and navigation for LoginEmailFragment
 * @see LoginEmailFragment
 */

public interface LoginEmailScreen
{
  void registerEvents(LoginEmailPresenter presenter);

  void startInventoryActivity();

  void showToast(String text);

  void showDialog(String text);

  void hideDialog();
}
