/**
 * Created by YuGang Yang on April 01, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.tudou.calendarpager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tudou.calendarpager.ui.fragment.SimpleFragment;

public class ContentPagerAdapter extends FragmentPagerAdapter {

  public ContentPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    return SimpleFragment.newInstance("This is the page: " + (position + 1));
  }

  @Override public int getCount() {
    return 21;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {


      default:
        return "";
    }
  }
}
