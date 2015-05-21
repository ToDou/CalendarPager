package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.test.tudou.library.R;
import com.test.tudou.library.expandcalendar.adapter.MonthViewAdapter;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;

/**
 * Created by tudou on 15-5-18.
 */
public class ExpandCalendarView extends LinearLayout implements ExpandCalendarMonthView.OnDayClickListener {

  @InjectView(android.R.id.content) ExpandMonthRecyclerView mRecyclerView;
  @InjectView(android.R.id.button1) View mBtnView;

  private ExpandCalendarMonthView.OnDayClickListener mOnDayClickListener;

  private MonthViewAdapter mMonthAdapter;

  public ExpandCalendarView(Context context) {
    this(context, null);
  }

  public ExpandCalendarView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExpandCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    initialize(context, attrs, defStyleAttr);
  }

  private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.view_expand_calendar_view, this);
    ButterKnife.inject(this);

    mMonthAdapter = new MonthViewAdapter(context, this);
    mRecyclerView.setAdapter(mMonthAdapter);

    mBtnView.setOnTouchListener(new OnTouchListener() {

      int startY;

      @Override public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN: // 手指按下时候的位置
            startY = (int) event.getRawY();
            break;
          case MotionEvent.ACTION_UP:

            break;
          case MotionEvent.ACTION_MOVE: // 触屏移动
            int y = (int) event.getRawY();
            int dy = y - startY;
            ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
            layoutParams.height += dy;
            startY += dy;
            mRecyclerView.getParent().requestLayout();
          default:
            break;
        }
        return true;
      }
    });
  }

  public void setData(CalendarDay startDay, CalendarDay endDay) {
    mMonthAdapter.setData(startDay, endDay, null);
  }

  public void setSelectDay(CalendarDay calendarDay) {
    mRecyclerView.scrollToPosition(DayUtils.calculateMonthPosition(mMonthAdapter.getStartDay(), calendarDay));
    mMonthAdapter.setSelectDay(calendarDay);
  }

  public void setOnDayClickListener(ExpandCalendarMonthView.OnDayClickListener onDayClickListener) {
    mOnDayClickListener = onDayClickListener;
  }

  @Override public void onDayClick(CalendarDay calendarDay) {
    mOnDayClickListener.onDayClick(calendarDay);
  }
}
