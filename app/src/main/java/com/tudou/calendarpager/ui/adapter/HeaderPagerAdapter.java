/**
 * Created by YuGang Yang on April 01, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.tudou.calendarpager.ui.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tudou.calendarpager.ui.view.WeekView;
import java.util.ArrayList;

public class HeaderPagerAdapter extends PagerAdapter {


  //界面列表
  private ArrayList<WeekView> views;

  private int mSelectDay;

  public HeaderPagerAdapter (Context context){
    views = new ArrayList<>();
    for (int i = 0; i < 3; i ++) {
      WeekView weekView = new WeekView(context);
      views.add(weekView);
    }
  }

  public void setSelectDay(int day) {
    mSelectDay = day;
    notifyDataSetChanged();
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView(views.get(position));
  }

  @Override public void finishUpdate(ViewGroup container) {
    super.finishUpdate(container);
  }

  //获得当前界面数
  @Override
  public int getCount() {
    if (views != null)
    {
      return views.size();
    }

    return 0;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    views.get(position).setSelectDay(mSelectDay);
    views.get(position).invalidate();
    container.addView(views.get(position));
    return views.get(position);
  }

  //判断是否由对象生成界面
  @Override
  public boolean isViewFromObject(View arg0, Object arg1) {
    return (arg0 == arg1);
  }

  @Override
  public void restoreState(Parcelable arg0, ClassLoader arg1) {
    // TODO Auto-generated method stub

  }

  @Override
  public Parcelable saveState() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override public void startUpdate(ViewGroup container) {
    super.startUpdate(container);
    for (int i = 0; i < container.getChildCount(); i++) {
      ((WeekView)container.getChildAt(i)).setSelectDay(mSelectDay);
      ((WeekView)container.getChildAt(i)).invalidate();
    }
  }
}
