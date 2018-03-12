package io.blockv.example.feature.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.feature.BaseActivity;

/**
 * The RegisterActivity demonstrates creating a new user
 *
 * @see RegisterPresenterImpl
 */
public class RegisterActivity extends BaseActivity {

  public static Intent getIntent(Context context) {
    return new Intent(context, RegisterActivity.class);
  }

  RegisterPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    RegisterScreen screen = new RegisterScreenImpl(this);
    presenter = new RegisterPresenterImpl(screen);
    screen.registerEvents(presenter);
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
