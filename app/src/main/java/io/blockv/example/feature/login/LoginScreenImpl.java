package io.blockv.example.feature.login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.login.email.LoginEmailFragment;
import io.blockv.example.feature.login.phone.LoginPhoneFragment;
import io.blockv.example.utils.FragmentViewPagerAdapter;

public class LoginScreenImpl extends BaseScreen implements LoginScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final TabLayout tabLayout;
  final ViewPager viewPager;

  public LoginScreenImpl(LoginActivity activity) {
    super(activity);

    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
    if(activity.getSupportActionBar()!=null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    viewPager = activity.findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    tabLayout = activity.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(activity.getSupportFragmentManager());
    adapter.addFragment(new LoginPhoneFragment(),activity.getResources().getString(R.string.login_page_phone_number));
    adapter.addFragment(new LoginEmailFragment(),activity.getResources().getString(R.string.login_page_email));
    viewPager.setAdapter(adapter);
  }

}

