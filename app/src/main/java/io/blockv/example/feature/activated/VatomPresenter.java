package io.blockv.example.feature.activated;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/**
 * VatomPresenter handles business logic for VatomActivity
 * @see VatomActivity
 */

public interface VatomPresenter {

  /**
   *  Load vatom details on activity create and update VatomScreen
   * @param intent
   */
  void onCreate(Intent intent);

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);

}
