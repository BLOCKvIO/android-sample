package io.blockv.example.feature.login;

/**
 * LoginScreen handles screen layout for LoginActivity
 * @see LoginActivity
 */
public interface LoginScreen
{
  void showToast(String text);

  void showDialog(String text);

  void hideDialog();

  void finish();
}
