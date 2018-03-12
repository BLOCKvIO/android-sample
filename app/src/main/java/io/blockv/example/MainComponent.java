package io.blockv.example;

import io.blockv.example.feature.BaseActivity;
import io.blockv.example.feature.BaseFragment;
import io.blockv.example.feature.BasePresenter;
import io.blockv.example.feature.BaseScreen;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(
  modules = {
    ApplicationModule.class,
    BlockvModule.class
  }
)
public interface MainComponent {

  void inject(BaseFragment fragment);

  void inject(BaseActivity activity);

  void inject(BaseScreen baseScreen);

  void inject(BasePresenter basePresenter);

}
