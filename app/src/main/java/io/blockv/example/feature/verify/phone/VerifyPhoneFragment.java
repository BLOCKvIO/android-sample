package io.blockv.example.feature.verify.phone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BaseFragment;

/**
 * The VerifyPhoneFragment demonstrates verifying a user's phone number
 *
 * @see VerifyPhonePresenterImpl
 */
public class VerifyPhoneFragment extends BaseFragment {


  public static VerifyPhoneFragment newFragment(String phone) {
    VerifyPhoneFragment fragment = new VerifyPhoneFragment();
    Bundle args = new Bundle();
    args.putString(Extras.PHONE_NUMBER, phone);
    fragment.setArguments(args);
    return fragment;
  }


  VerifyPhonePresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_verify_phone, container, false);
    VerifyPhoneScreen screen = new VerifyPhoneScreenImpl(view,this);
    presenter = new VerifyPhonePresenterImpl(screen);
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
