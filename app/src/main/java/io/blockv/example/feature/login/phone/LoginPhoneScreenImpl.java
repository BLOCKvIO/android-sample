package io.blockv.example.feature.login.phone;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.inventory.InventoryActivity;



public class LoginPhoneScreenImpl extends BaseScreen implements LoginPhoneScreen {

  final Button login;
  final Button otp;
  final EditText username;
  final EditText password;

  public LoginPhoneScreenImpl(View view, LoginPhoneFragment fragment) {
    super(fragment);

    login = view.findViewById(R.id.login);
    otp = view.findViewById(R.id.otp);
    username = view.findViewById(R.id.username);
    password = view.findViewById(R.id.password);
  }


  @Override
  public void registerEvents(LoginPhonePresenter presenter) {

    login.setOnClickListener(view -> presenter.onLoginButtonClick(view,
      username.getText().toString(),
      password.getText().toString()));

    otp.setOnClickListener(view -> presenter.onSendOtpButtonClick(view,
      username.getText().toString()));
  }

  @Override
  public void startInventoryActivity() {
    Intent intent = InventoryActivity.getIntent(activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
  }


}

