package io.blockv.example.feature.activated;

import io.blockv.core.model.Token;
import io.blockv.core.model.Vatom;

import java.util.List;

/**
 * VatomScreen handles screen layout for VatomActivity
 * @see VatomActivity
 */

public interface VatomScreen {

  void registerEvents(VatomPresenter presenter);

  void finish();

  void showDialog(String text);

  void hideDialog();

  void showToast(String message);

  void setVatom(Vatom vatom);

}
