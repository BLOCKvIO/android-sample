package io.blockv.example.feature;

import android.support.v7.app.AppCompatActivity;
import io.blockv.core.client.manager.UserManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.example.Injector;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity
{

  @Inject
  UserManager userManager;
  @Inject
  VatomManager vatomManager;

  public BaseActivity() {
    super();
    Injector.$.inject(this);
  }


}
