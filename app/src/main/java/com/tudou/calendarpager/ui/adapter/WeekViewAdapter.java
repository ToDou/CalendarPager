package com.tudou.calendarpager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tudou.calendarpager.model.CalendarDay;
import com.tudou.calendarpager.model.CalendarWeek;
import com.tudou.calendarpager.ui.view.WeekView;
import com.tudou.calendarpager.util.DayUtils;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tudou on 15-4-30.
 */
public class WeekViewAdapter extends RecyclerView.Adapter<WeekViewAdapter.WeekViewHolder> {
  private final static int DAY_IN_WEEK = 7;

  private LayoutInflater mInflater;
  private Context mContext;
  private CalendarDay mStartDay;
  private CalendarDay mEndDay;
  private CalendarDay mFirstShowDay;

  public WeekViewAdapter(Context context, CalendarDay startDay, CalendarDay endDay) {
    mInflater = LayoutInflater.from(context);
    mContext = context;
    mStartDay = startDay;
    mEndDay = endDay;
    calculateFirstShowDay();
  }

  private void calculateFirstShowDay() {
    int day = mStartDay.calendar.get(Calendar.DAY_OF_WEEK);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(mStartDay.getTime());
    calendar.roll(Calendar.DAY_OF_YEAR, -day + 1);
    mFirstShowDay = new CalendarDay(calendar);
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
    WeekViewHolder viewHolder = new WeekViewHolder(weekView, mFirstShowDay, mStartDay, mEndDay);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final WeekViewHolder viewHolder, final int position) {
    viewHolder.bind(position);
  }

  public static class WeekViewHolder extends RecyclerView.ViewHolder {

    private WeekView mWeekView;


    public WeekViewHolder(View view,CalendarDay firstShowDay, CalendarDay startDay, CalendarDay endDay) {
      super(view);
      mWeekView = (WeekView)view;
      mWeekView.setDays(firstShowDay, startDay, endDay);
    }

    public void bind(int position) {
      mWeekView.setPosition(position);
    }

  }


}
