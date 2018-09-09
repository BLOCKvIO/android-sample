package io.blockv.example.feature.activated;


import io.blockv.common.model.Vatom;

/**
 * ActivatedScreen handles screen layout for ActivatedActivity
 * @see ActivatedActivity
 */

public interface ActivatedScreen {

  void registerEvents(ActivatedPresenter presenter);

  void finish();

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void setVatom(Vatom vatom);

  void startVatomDetailsActivity(String vatomId);

}
