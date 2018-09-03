package io.blockv.example.feature.activated;

import io.blockv.core.model.Vatom;

/**
 * VatomMetaScreen handles screen layout for ActivatedActivity
 * @see ActivatedActivity
 */

public interface VatomMetaScreen {

  void registerEvents(ActivatedPresenter presenter);

  void finish();

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void setVatom(Vatom vatom);

}
