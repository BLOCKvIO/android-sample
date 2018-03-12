package io.blockv.example.feature.verify.email;

/**
 * VerifyEmailScreen handles screen layout and navigation for VerifyEmailFragment
 * @see VerifyEmailFragment
 */

public interface VerifyEmailScreen
{
  void registerEvents(VerifyEmailPresenter presenter);

  void setEmail(String phoneNumber);

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void startInventoryActivity();
}
