package com.test.tudou.library.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.ui.view.WeekDayViewPager;
import com.test.tudou.library.ui.view.WeekView;
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

  public WeekViewAdapter(Context context, WeekDayViewPager viewPager) {
    mContext = context;
    mWeekDayViewPager = viewPager;
    mAbleCalendayDays = new ArrayList<>();
  }

  public void setData(CalendarDay startDay, CalendarDay endDay, ArrayList<CalendarDay> calendarDayArrayList) {
    mStartDay = startDay;
    mEndDay = endDay;
    mFirstShowDay = DayUtils.calculateFirstShowDay(mStartDay);
    mAbleCalendayDays.clear();
    mAbleCalendayDays.addAll(calendarDayArrayList);
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
    WeekView weekView = new WeekView(mContext);
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
      mWeekView.setAbleDates(mAbleCalendayDays);
    }

    public void bind(int position, int selectPostion) {
      mWeekView.setPosition(position);
      mWeekView.setSelectPostion(selectPostion);
    }

  }


}
