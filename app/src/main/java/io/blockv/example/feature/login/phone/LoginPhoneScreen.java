package io.blockv.example.feature.login.phone;

/**
 * Created by LordCheddar on 2018/01/10.
 */

public interface LoginPhoneScreen
{
  void registerEvents(LoginPhonePresenter presenter);

  void startInventoryActivity();

  void showToast(String text);

  void showDialog(String text);

  void hideDialog();
}
