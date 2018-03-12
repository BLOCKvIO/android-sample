package io.blockv.example.feature.verify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseActivity;

/**
 * The VerifyActivity demonstrates verifying a user's token
 *
 * @see io.blockv.example.feature.verify.email.VerifyEmailPresenterImpl
 * @see io.blockv.example.feature.verify.phone.VerifyPhonePresenterImpl
 */
public class VerifyActivity extends BaseActivity {

  public static Intent getIntent(Context context, String phoneNumber,String email) {
    Intent intent = new Intent(context, VerifyActivity.class);
    intent.putExtra(Extras.PHONE_NUMBER, phoneNumber);
    intent.putExtra(Extras.EMAIL, email);
    return intent;
  }

  VerifyPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_verify);
    VerifyScreen screen = new VerifyScreenImpl(this);
    presenter = new VerifyPresenterImpl(screen);
    presenter.onCreate(getIntent());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    presenter.onOptionsItemSelected(menuItem);
    return super.onOptionsItemSelected(menuItem);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_verify, menu);
    return super.onCreateOptionsMenu(menu);
  }
}
