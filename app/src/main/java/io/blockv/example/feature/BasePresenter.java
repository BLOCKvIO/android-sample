package io.blockv.example.feature;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import io.blockv.core.client.manager.UserManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.core.util.CompositeCancellable;
import io.blockv.core.util.Cancellable;
import io.blockv.example.Injector;

import javax.inject.Inject;
import javax.inject.Named;


public class BasePresenter
{

  final CompositeCancellable disposables;

  @Inject
  protected Resources resources;

  @Inject
  protected UserManager userManager;

  @Inject
  protected VatomManager vatomManager;


  protected Handler handler = new Handler(Looper.getMainLooper());

  protected BasePresenter() {
    this.disposables = new CompositeCancellable();
    Injector.$.inject(this);
  }

  public void collect(Cancellable disposable) {
    if(disposable!=null) {
      disposables.add(disposable);
    }
  }

  public void dispose() {
    disposables.cancel();
    disposables.clear();
  }

  public String getString(@StringRes int id) {
    return resources.getString(id);
  }

}
