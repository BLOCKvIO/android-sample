package io.blockv.example.feature.verify;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.inventory.InventoryActivity;
import io.blockv.example.feature.verify.email.VerifyEmailFragment;
import io.blockv.example.feature.verify.phone.VerifyPhoneFragment;
import io.blockv.example.utils.FragmentViewPagerAdapter;


public class VerifyScreenImpl extends BaseScreen implements VerifyScreen {
  final android.support.v7.widget.Toolbar toolbar;
  final TabLayout tabLayout;
  final ViewPager viewPager;

  public VerifyScreenImpl(VerifyActivity activity) {

    super(activity);

    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    viewPager = activity.findViewById(R.id.viewpager);
    tabLayout = activity.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public void setDetails(String phone, String email) {
    FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(activity.getSupportFragmentManager());
    if(!TextUtils.isEmpty(phone)) {
      adapter.addFragment(VerifyPhoneFragment.newFragment(phone), activity.getResources().getString(R.string.verify_page_phone_number));
    }
    if(!TextUtils.isEmpty(email)) {
      adapter.addFragment(VerifyEmailFragment.newFragment(email), activity.getResources().getString(R.string.verify_page_email));
    }
    viewPager.setAdapter(adapter);
  }


  @Override
  public void startInventoryActivity() {
    Intent intent = InventoryActivity.getIntent(activity);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
  }
}

