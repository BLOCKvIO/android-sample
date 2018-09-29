package io.blockv.example.feature.details;

import android.content.Intent;
import android.view.MenuItem;

/**
 * VatomMetaPresenter handles business logic for VatomMetaActivity
 * @see VatomMetaActivity
 */

public interface VatomMetaPresenter {

  /**
   * Load vatom details on activity create and update VatomMetaScreen
   * @param intent
   */
  void onCreate(Intent intent);

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);

}
