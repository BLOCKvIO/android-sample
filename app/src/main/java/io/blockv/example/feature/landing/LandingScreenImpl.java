package io.blockv.example.feature.landing;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toolbar;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.inventory.InventoryActivity;
import io.blockv.example.feature.login.LoginActivity;
import io.blockv.example.feature.register.RegisterActivity;


public class LandingScreenImpl extends BaseScreen implements LandingScreen {

  final Button login;
  final Button register;
  final android.support.v7.widget.Toolbar toolbar;

  public LandingScreenImpl(LandingActivity activity) {
    super(activity);

    login = activity.findViewById(R.id.login);
    register = activity.findViewById(R.id.register);
    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
  }

  @Override
  public void registerEvents(LandingPresenter presenter) {
    login.setOnClickListener(presenter::onLoginButtonClick);
    register.setOnClickListener(presenter::onRegisterButtonClick);
  }

  @Override
  public void startLoginActivity() {
    activity.startActivity(LoginActivity.getIntent(activity));
  }

  @Override
  public void startRegisterActivity() {
    activity.startActivity(RegisterActivity.getIntent(activity));
  }

  @Override
  public void startInventoryActivity() {
    Intent intent = InventoryActivity.getIntent(activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
  }
}

