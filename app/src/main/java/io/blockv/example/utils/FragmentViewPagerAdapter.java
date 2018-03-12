package io.blockv.example.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
  private final List<String> titles = new ArrayList<>();
  private final List<Fragment> fragments = new ArrayList<>();

  public FragmentViewPagerAdapter(FragmentManager manager) {
    super(manager);
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  public void addFragment(Fragment fragment,String title) {
    fragments.add(fragment);
    titles.add(title);
  }

  public Fragment getFragment(int position) {
    return fragments.get(position);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }
}

