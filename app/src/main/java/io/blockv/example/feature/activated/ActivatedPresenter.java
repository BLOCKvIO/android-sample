package io.blockv.example.feature.activated;

import android.content.Intent;
import android.view.MenuItem;

/**
 * ActivatedPresenter handles business logic for ActivatedActivity
 * @see ActivatedActivity
 */

public interface ActivatedPresenter {

  /**
   * Load vatom details on activity create and update ActivatedScreen
   * @param intent
   */
  void onCreate(Intent intent);

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);

}
