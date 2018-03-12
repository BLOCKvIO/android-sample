package io.blockv.example.feature.verify.email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseFragment;

/**
 * The VerifyEmailFragment demonstrates verifying a user's email address
 *
 * @see VerifyEmailPresenterImpl
 */
public class VerifyEmailFragment extends BaseFragment {


  public static VerifyEmailFragment newFragment(String email) {
    VerifyEmailFragment fragment = new VerifyEmailFragment();
    Bundle args = new Bundle();
    args.putString(Extras.EMAIL, email);
    fragment.setArguments(args);
    return fragment;
  }


  VerifyEmailPresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_verify_email, container, false);
    VerifyEmailScreen screen = new VerifyEmailScreenImpl(view,this);
    presenter = new VerifyEmailPresenterImpl(screen);
    screen.registerEvents(presenter);
    presenter.onCreateView(getArguments());
    return view;
  }
  @Override
  public void onDestroyView() {
    presenter.onDestroy();
    super.onDestroyView();
  }



}
