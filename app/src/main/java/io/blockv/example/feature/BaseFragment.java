package io.blockv.example.feature;


import android.support.v4.app.Fragment;
import io.blockv.core.client.manager.UserManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.example.Injector;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {
  @Inject
  UserManager userManager;
  @Inject
  VatomManager vatomManager;


  public BaseFragment() {
    super();
    Injector.$.inject(this);
  }

  @Override
  public void setUserVisibleHint(boolean visible) {
    super.setUserVisibleHint(visible);

    if (isResumed()) {
      if (visible) {
        //Only manually call onResume if fragment is already visible
        //Otherwise allow natural fragment lifecycle to call onResume
        onResume();
      } else {
        onPause();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (!getUserVisibleHint()) {
      return;
    }
    onFragmentResume();

  }

  @Override
  public void onPause() {
    super.onPause();
    onFragmentPause();
  }

  public void onFragmentResume() {
  }

  public void onFragmentPause() {
  }
}
