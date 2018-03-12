package io.blockv.example.feature.login;

import android.view.MenuItem;

/**
 * LoginPresenter handles business logic for LoginActivity
 * @see LoginActivity
 */
public interface LoginPresenter {

  void onOptionsItemSelected(MenuItem menuItem);

  void onDestroy();
}
