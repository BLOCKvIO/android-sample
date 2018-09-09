package io.blockv.example.feature.details;


import io.blockv.common.model.Vatom;

/**
 * VatomMetaScreen handles screen layout for VatomMetaActivity
 * @see VatomMetaActivity
 */

public interface VatomMetaScreen {

  void registerEvents(VatomMetaPresenter presenter);

  void finish();

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void setVatom(Vatom vatom);

}
