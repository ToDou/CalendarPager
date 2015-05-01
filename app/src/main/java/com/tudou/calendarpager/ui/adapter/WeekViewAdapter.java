package com.tudou.calendarpager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.tudou.calendarpager.model.CalendarDay;
import com.tudou.calendarpager.ui.view.WeekDayViewPager;
import com.tudou.calendarpager.ui.view.WeekView;
import com.tudou.calendarpager.util.DayUtils;

/**
 * Created by tudou on 15-4-30.
 */
public class WeekViewAdapter extends RecyclerView.Adapter<WeekViewAdapter.WeekViewHolder> implements
    WeekView.OnDayClickListener {

  private Context mContext;
  private CalendarDay mStartDay;
  private CalendarDay mEndDay;
  private CalendarDay mFirstShowDay;
  private WeekDayViewPager mWeekDayViewPager;
  private int mSelectPosition;

  public WeekViewAdapter(Context context, CalendarDay startDay, CalendarDay endDay, WeekDayViewPager viewPager) {
    mContext = context;
    mStartDay = startDay;
    mEndDay = endDay;
    mWeekDayViewPager = viewPager;
    mFirstShowDay = DayUtils.calculateFirstShowDay(mStartDay);
  }

  @Override
  public int getItemCount() {
    int weekCount = DayUtils.calculateWeekCount(mStartDay, mEndDay);
    return weekCount;
  }



  @Override
  public WeekViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    WeekView weekView = new WeekView(mContext);
    int width = mContext.getResources().getDisplayMetrics().widthPixels;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
        ViewGroup.LayoutParams.MATCH_PARENT);
    weekView.setLayoutParams(params);
    WeekViewHolder viewHolder = new WeekViewHolder(weekView, mFirstShowDay, mWeekDayViewPager, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final WeekViewHolder viewHolder, final int position) {
    viewHolder.bind(position, mSelectPosition);
  }

  @Override public void onDayClick(WeekView simpleMonthView, CalendarDay calendarDay,
      int position) {
    mSelectPosition = position;
    notifyDataSetChanged();
  }

  public static class WeekViewHolder extends RecyclerView.ViewHolder {

    private WeekView mWeekView;


    public WeekViewHolder(View view,CalendarDay firstShowDay, WeekDayViewPager viewPager, WeekViewAdapter weekViewAdapter) {
      super(view);
      mWeekView = (WeekView)view;
      mWeekView.setDays(firstShowDay);
      mWeekView.setOnDayClickListener(viewPager);
      mWeekView.setOnAdapterDayClickListener(weekViewAdapter);
    }

    public void bind(int position, int selectPostion) {
      mWeekView.setPosition(position);
      mWeekView.setSelectPostion(selectPostion);
    }

  }


}
