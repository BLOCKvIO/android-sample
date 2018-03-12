package io.blockv.example.feature.verify;

import android.content.Intent;
import android.view.MenuItem;

/**
 * VerifyPresenter handles business logic for VerifyActivity
 * @see VerifyActivity
 */
public interface VerifyPresenter {

  void onCreate(Intent intent);

  void onOptionsItemSelected(MenuItem menuItem);

}
