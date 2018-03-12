package io.blockv.example;

import android.app.Application;
import android.content.res.Resources;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {

  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Singleton
  @Provides
  public Application provideApplication() {
    return application;
  }

  @Singleton
  @Provides
  public Resources provideResources() {
    return application.getResources();
  }

  @Singleton
  @Provides
  public Picasso providePicasso() {
    return new Picasso.Builder(application).build();
  }

}
