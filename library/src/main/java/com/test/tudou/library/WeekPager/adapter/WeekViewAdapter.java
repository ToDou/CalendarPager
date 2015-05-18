package com.test.tudou.library.WeekPager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.test.tudou.library.R;
import com.test.tudou.library.WeekPager.view.WeekDayViewPager;
import com.test.tudou.library.WeekPager.view.WeekView;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import java.util.ArrayList;

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
  private ArrayList<CalendarDay> mAbleCalendayDays;

  /**
   * week view value
   */
  private int mTextNormalColor;
  private int mTextSelectColor;
  private int mTextUnableColor;
  private int indicatorColor;

  public WeekViewAdapter(Context context, WeekDayViewPager viewPager) {
    mContext = context;
    mWeekDayViewPager = viewPager;
    mAbleCalendayDays = new ArrayList<>();
    updateColor();
  }

  private void updateColor() {
    indicatorColor = mContext.getResources().getColor(R.color.color_18ffff);
    mTextSelectColor = mContext.getResources().getColor(android.R.color.white);
    mTextNormalColor = mContext.getResources().getColor(R.color.text_color_normal);
    mTextUnableColor = mContext.getResources().getColor(R.color.text_color_light);
  }

  public void setData(CalendarDay startDay, CalendarDay endDay, ArrayList<CalendarDay> calendarDayArrayList) {
    mStartDay = startDay;
    mEndDay = endDay;
    mFirstShowDay = DayUtils.calculateFirstShowDay(mStartDay);
    if (calendarDayArrayList != null) {
      mAbleCalendayDays.clear();
      mAbleCalendayDays.addAll(calendarDayArrayList);
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (mStartDay == null || mEndDay == null) {
      return 0;
    }
    int weekCount = DayUtils.calculateWeekCount(mStartDay, mEndDay);
    return weekCount;
  }



  @Override
  public WeekViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    WeekView weekView = new WeekView(mContext, mTextNormalColor, mTextSelectColor, mTextUnableColor, indicatorColor);
    int width = mContext.getResources().getDisplayMetrics().widthPixels;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
        ViewGroup.LayoutParams.MATCH_PARENT);
    weekView.setLayoutParams(params);
    WeekViewHolder viewHolder = new WeekViewHolder(weekView, mFirstShowDay, mWeekDayViewPager, mAbleCalendayDays, this);
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


    public WeekViewHolder(View view,CalendarDay firstShowDay, WeekDayViewPager viewPager, ArrayList<CalendarDay> mAbleCalendayDays, WeekViewAdapter weekViewAdapter) {
      super(view);
      mWeekView = (WeekView)view;
      mWeekView.setDays(firstShowDay);
      mWeekView.setOnDayClickListener(viewPager);
      mWeekView.setOnAdapterDayClickListener(weekViewAdapter);
      mWeekView.setTextSize(view.getContext().getResources().getDimension(R.dimen.si_default_text_size));
      mWeekView.setAbleDates(mAbleCalendayDays);
    }

    public void bind(int position, int selectPostion) {
      mWeekView.setPosition(position);
      mWeekView.setSelectPostion(selectPostion);
    }

  }

  public void setTextNormalColor(int mTextNormalColor) {
    this.mTextNormalColor = mTextNormalColor;
  }

  public void setTextSelectColor(int mTextSelectColor) {
    this.mTextSelectColor = mTextSelectColor;
  }

  public void setTextUnableColor(int mTextUnableColor) {
    this.mTextUnableColor = mTextUnableColor;
  }

  public void setIndicatorColor(int indicatorColor) {
    this.indicatorColor = indicatorColor;
  }
}
