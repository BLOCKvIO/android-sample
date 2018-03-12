package io.blockv.example.feature.login.email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.blockv.example.R;
import io.blockv.example.feature.BaseFragment;


/**
 * The LoginEmailFragment demonstrates logging a user into the blockv platform using an email address
 *
 * @see LoginEmailPresenterImpl
 */
public class LoginEmailFragment extends BaseFragment {

  LoginEmailPresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login_email, container, false);
    LoginEmailScreen screen = new LoginEmailScreenImpl(view,this);
    presenter = new LoginEmailPresenterImpl(screen);
    screen.registerEvents(presenter);
    return view;
  }

  @Override
  public void onDestroyView() {
    presenter.onDestroy();
    super.onDestroyView();
  }


}
