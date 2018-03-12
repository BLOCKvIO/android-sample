package io.blockv.example.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.feature.BaseActivity;

/**
 * The Login Activity demonstrates logging a user into the blockv platform with either a phone number or email
 *
 * @see io.blockv.example.feature.login.phone.LoginPhonePresenterImpl
 * @see io.blockv.example.feature.login.email.LoginEmailPresenterImpl
 */
public class LoginActivity extends BaseActivity {

  public static Intent getIntent(Context context) {
    return new Intent(context, LoginActivity.class);
  }

  LoginPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    LoginScreen screen = new LoginScreenImpl(this);
    presenter = new LoginPresenterImpl(screen);
  }

  public void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    presenter.onOptionsItemSelected(menuItem);
    return super.onOptionsItemSelected(menuItem);
  }
}
