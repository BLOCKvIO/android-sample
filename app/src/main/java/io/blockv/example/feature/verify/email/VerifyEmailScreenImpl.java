package io.blockv.example.feature.verify.email;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.inventory.InventoryActivity;


public class VerifyEmailScreenImpl extends BaseScreen implements VerifyEmailScreen {
  final TextView email;
  final EditText code;
  final Button verify;
  final Button resend;

  public VerifyEmailScreenImpl(View view, VerifyEmailFragment activity) {

    super(activity);

    email = view.findViewById(R.id.email);
    code = view.findViewById(R.id.otp);
    verify = view.findViewById(R.id.verify);
    resend = view.findViewById(R.id.resend_otp);
  }

  @Override
  public void registerEvents(VerifyEmailPresenter presenter) {
    verify.setOnClickListener(view -> presenter.onVerifyButtonClicked(view, email.getText().toString(), code.getText().toString()));
    resend.setOnClickListener(view -> presenter.onResendOtpButtonClicked(view, email.getText().toString()));
  }

  @Override
  public void setEmail(String emailAddress) {
    email.setText(emailAddress);
  }

  @Override
  public void startInventoryActivity() {

    Intent intent = InventoryActivity.getIntent(activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
  }
}

