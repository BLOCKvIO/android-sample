package io.blockv.example.feature.profile;

import io.blockv.core.model.Token;

import java.util.List;

/**
 * VatomMetaScreen handles screen layout for VatomActivity
 * @see ProfileActivity
 */

public interface ProfileScreen {
  void registerEvents(ProfilePresenter presenter);

  void finish();

  void setFirstName(String firstName);

  void setLastName(String lastName);

  void setBirthday(String birthday);

  void setAvatar(String url);

  void setUserId(String id);

  void enableSaveDetails(boolean enable);

  void enableSavePassword(boolean enable);

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void startSelectPhotoActivity(int code);

  void restartApp();

  void setTokens(List<Token> tokens);
}
