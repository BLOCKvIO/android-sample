package io.blockv.example.feature.register;

import android.view.MenuItem;
import android.view.View;

/**
 * RegisterPresenter handles business logic for RegisterActivity
 * @see RegisterActivity
 */
public interface RegisterPresenter {

  /**
   * Attempt to register a new user
   * Either email or phone number need to be provided
   *
   * @param view
   * @param firstName is the user's first name
   * @param lastName is the user's last name
   * @param password is the user's desired password
   * @param email is the user's email address
   * @param phoneNumber is the user' phone number
   */
  void onRegisterButtonClick(View view,
                             String firstName,
                             String lastName,
                             String password,
                             String email,
                             String phoneNumber);

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);

}
