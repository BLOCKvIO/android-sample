package io.blockv.example.feature.landing;

import android.os.Bundle;
import io.blockv.example.R;
import io.blockv.example.feature.BaseActivity;

/**
 * The Landing Activity demonstrates checking for a logged in user
 *
 * @see LandingPresenterImpl
 */
public class LandingActivity extends BaseActivity {

  LandingPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);
    LandingScreen screen = new LandingScreenImpl(this);
    presenter = new LandingPresenterImpl(screen);
    screen.registerEvents(presenter);
    presenter.onCreate();
  }


}
