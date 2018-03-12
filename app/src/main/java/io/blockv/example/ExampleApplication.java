package io.blockv.example;

import android.app.Application;

public class ExampleApplication extends Application {
  
  @Override
  public void onCreate() {
    super.onCreate();
    Injector.$.init(this);
  }
  
}
