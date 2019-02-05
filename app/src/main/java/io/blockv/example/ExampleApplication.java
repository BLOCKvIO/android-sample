package io.blockv.example;

import android.app.Application;
import android.webkit.WebView;

public class ExampleApplication extends Application {
  
  @Override
  public void onCreate() {
    super.onCreate();
    WebView.setWebContentsDebuggingEnabled(true);
    Injector.$.init(this);
  }
  
}
