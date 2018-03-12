package io.blockv.example.feature.login.phone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.blockv.example.R;
import io.blockv.example.feature.BaseFragment;

/**
 * The LoginPhoneFragment demonstrates logging a user into the blockv platform using a phone number
 *
 * @see LoginPhonePresenterImpl
 */
public class LoginPhoneFragment extends BaseFragment {

  LoginPhonePresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login_phone, container, false);
    LoginPhoneScreen screen = new LoginPhoneScreenImpl(view,this);
    presenter = new LoginPhonePresenterImpl(screen);
    screen.registerEvents(presenter);
    return view;
  }

  @Override
  public void onDestroyView() {
    presenter.onDestroy();
    super.onDestroyView();
  }
}
