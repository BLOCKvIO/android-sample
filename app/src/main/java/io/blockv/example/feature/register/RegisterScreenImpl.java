package io.blockv.example.feature.register;

import android.widget.Button;
import android.widget.EditText;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.verify.VerifyActivity;

public class RegisterScreenImpl extends BaseScreen implements RegisterScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final EditText phone;
  final EditText email;
  final EditText firstName;
  final EditText lastName;
  final EditText password;
  final Button register;

  public RegisterScreenImpl(RegisterActivity activity) {
    super(activity);
    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
    if(activity.getSupportActionBar()!=null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    phone = activity.findViewById(R.id.phone_number);
    email = activity.findViewById(R.id.email);
    firstName = activity.findViewById(R.id.first_name);
    lastName = activity.findViewById(R.id.last_name);
    password = activity.findViewById(R.id.password);
    register = activity.findViewById(R.id.register);
  }

  @Override
  public void registerEvents(RegisterPresenter presenter) {

    register.setOnClickListener(
      view -> presenter.onRegisterButtonClick(view,
        firstName.getText().toString(),
        lastName.getText().toString(),
        password.getText().toString(),
        email.getText().toString(),
        phone.getText().toString()));

  }

  @Override
  public void startVerifyActivity(String phoneNumber, String email) {
    activity.startActivity(VerifyActivity.getIntent(activity, phoneNumber, email));
  }


}

