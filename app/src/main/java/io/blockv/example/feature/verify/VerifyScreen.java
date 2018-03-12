package io.blockv.example.feature.verify;

/**
 * VerifyScreen handles screen layout and navigation for VerifyActivity
 * @see VerifyActivity
 */

public interface VerifyScreen
{

  void setDetails(String phoneNumber,String email);

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void finish();

  void startInventoryActivity();

}
