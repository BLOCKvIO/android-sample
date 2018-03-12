package io.blockv.example.feature.verify;

import android.content.Intent;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;

public class VerifyPresenterImpl extends BasePresenter implements VerifyPresenter {

  private final VerifyScreen screen;
  private String phoneNumber;
  private String email;

  public VerifyPresenterImpl(VerifyScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate(Intent intent) {
    phoneNumber = intent.getStringExtra(Extras.PHONE_NUMBER);
    email = intent.getStringExtra(Extras.EMAIL);
    screen.setDetails(phoneNumber, email);
  }

  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      screen.finish();
    } else if (menuItem.getItemId() == R.id.skip) {
      screen.startInventoryActivity();
    }

  }
}
