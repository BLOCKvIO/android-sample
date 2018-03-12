package io.blockv.example.feature.verify.phone;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.inventory.InventoryActivity;


public class VerifyPhoneScreenImpl extends BaseScreen implements VerifyPhoneScreen {
  final TextView phone;
  final EditText code;
  final Button verify;
  final Button resend;

  public VerifyPhoneScreenImpl(View view, VerifyPhoneFragment activity) {

    super(activity);

    phone = view.findViewById(R.id.phone_number);
    code = view.findViewById(R.id.otp);
    verify = view.findViewById(R.id.verify);
    resend = view.findViewById(R.id.resend_otp);
  }

  @Override
  public void registerEvents(VerifyPhonePresenter presenter) {
    verify.setOnClickListener(view -> presenter.onVerifyButtonClicked(view, phone.getText().toString(), code.getText().toString()));
    resend.setOnClickListener(view -> presenter.onResendOtpButtonClicked(view, phone.getText().toString()));
  }

  @Override
  public void setPhoneNumber(String phoneNumber) {
    phone.setText(phoneNumber);
  }

  @Override
  public void startInventoryActivity() {

    Intent intent = InventoryActivity.getIntent(activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
  }
}

