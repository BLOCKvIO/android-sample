package io.blockv.example.feature.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/**
 * ProfilePresenter handles business logic for ProfileActivity
 * @see ProfileActivity
 */

public interface ProfilePresenter {

  /**
   *  Load the user's details on activity create and update ActivatedScreen
   * @param savedInstanceState
   */
  void onCreate(Bundle savedInstanceState);

  void onDestroy();

  void onOptionsItemSelected(MenuItem menuItem);

  /**
   * Updates the user details
   * @param view
   * @param firstName is the user updated first name
   * @param lastName is the user updated last name
   * @param birthday is the user updated birthday
   */
  void onSaveDetailsClick(View view, String firstName, String lastName, String birthday);

  /**
   * Updates the user's password
   * @param view
   * @param password
   */
  void onSavePasswordClick(View view, String password);

  /**
   * Log user out
   * @param view
   */
  void onLogOutClick(View view);

  /**
   * Updates the user's avatar
   * @param view
   */
  void onAvatarClick(View view);

  void onActivityResult(int requestCode, int resultCode, Intent data);
}
