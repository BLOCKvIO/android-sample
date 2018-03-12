package io.blockv.example.feature.verify.phone;

/**
 * VerifyPhoneScreen handles screen layout and navigation for VerifyPhoneFragment
 * @see VerifyPhoneFragment
 */

public interface VerifyPhoneScreen
{
  void registerEvents(VerifyPhonePresenter presenter);

  void setPhoneNumber(String phoneNumber);

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void startInventoryActivity();
}
