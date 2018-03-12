package io.blockv.example;

import android.app.Application;
import io.blockv.example.feature.BaseActivity;
import io.blockv.example.feature.BaseFragment;
import io.blockv.example.feature.BasePresenter;
import io.blockv.example.feature.BaseScreen;
import timber.log.Timber;

public enum Injector implements MainComponent {
  $;

  private MainComponent component;

  public final void init(Application application) {

    if (component != null) {
      throw new RuntimeException("Can't init twice!");
    }
    Timber.plant(new Timber.DebugTree());

    component = DaggerMainComponent.builder()
      .applicationModule(new ApplicationModule(application))
      .blockvModule(new BlockvModule(application))
      .build();
  }

  @Override
  public void inject(BaseFragment fragment) {
    component.inject(fragment);
  }

  @Override
  public void inject(BaseActivity activity) {
    component.inject(activity);
  }

  @Override
  public void inject(BaseScreen screen) {
    component.inject(screen);
  }

  @Override
  public void inject(BasePresenter presenter) {
    component.inject(presenter);
  }

}
