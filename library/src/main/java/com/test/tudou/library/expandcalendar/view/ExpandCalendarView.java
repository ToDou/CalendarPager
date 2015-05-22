package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.test.tudou.library.R;
import com.test.tudou.library.expandcalendar.adapter.MonthViewAdapter;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import com.test.tudou.library.util.UiUtils;

/**
 * Created by tudou on 15-5-18.
 */
public class ExpandCalendarView extends LinearLayout implements ExpandCalendarMonthView.OnDayClickListener {

  private final static String TAG = "ExpandCalendarView";

  private final static int MIN_OFFSET = 80;
  private final static int MAX_OFFSET = 200;

  private int minHeight;
  private int maxHeight;

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
    mBtnView.setAlpha(0.01f);
    minHeight = (int) UiUtils.calculateExpandMinHeight(getResources().getDimension(R.dimen.si_default_text_size), getResources().getDimensionPixelSize(R.dimen.default_month_row_height));
    maxHeight = getResources().getDimensionPixelSize(R.dimen.default_expand_view_max_height);

    updateRecyclerHeight();
    mBtnView.setOnTouchListener(new OnTouchListener() {

      int startY;

      @Override public boolean onTouch(View v, MotionEvent event) {
        final ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN: // 手指按下时候的位置
            startY = (int) event.getRawY();
            ObjectAnimator.ofFloat(mBtnView, "alpha", 0.01f, 1.0f).start();
            break;
          case MotionEvent.ACTION_UP:
            ObjectAnimator.ofFloat(mBtnView, "alpha", 1.0f, 0.01f).start();
            if (layoutParams.height - minHeight < MIN_OFFSET) {
              layoutParams.height = minHeight;
              mRecyclerView.getParent().requestLayout();
              adjustSelectPosition();
            } else if (maxHeight - layoutParams.height < MAX_OFFSET) {
              layoutParams.height = maxHeight;
              mRecyclerView.getParent().requestLayout();
            }

            break;
          case MotionEvent.ACTION_MOVE: // 触屏移动
            int y = (int) event.getRawY();
            int dy = y - startY;
            if (dy > 0 && !(layoutParams.height + dy < maxHeight)) {
              layoutParams.height = maxHeight;
            } else if (dy < 0 && !(layoutParams.height + dy > minHeight)) {
              layoutParams.height = minHeight;
            } else {
              layoutParams.height += dy;
              startY += dy;
            }
            mRecyclerView.getParent().requestLayout();
          default:
            break;
        }
        return true;
      }
    });
  }

  private void updateRecyclerHeight() {
    ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
    layoutParams.height = minHeight;
  }

  private void adjustSelectPosition() {
    mRecyclerView.scrollToSelectRow(mMonthAdapter.getStartDay(), mMonthAdapter.getSelectDay());
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
