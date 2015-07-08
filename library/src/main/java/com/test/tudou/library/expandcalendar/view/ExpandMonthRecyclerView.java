package com.test.tudou.library.expandcalendar.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.test.tudou.library.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import com.test.tudou.library.util.DisplayUtil;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tudou on 15-5-3.
 */
public class ExpandMonthRecyclerView extends RecyclerView {

  public static int LIST_LEFT_OFFSET = -1;
  private LinearLayoutManager mManager;

  public ExpandMonthRecyclerView(Context context) {
    this(context, null);
  }

  public ExpandMonthRecyclerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExpandMonthRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    initData();
  }

  private void initData() {
    mManager = new LinearLayoutManager(getContext());
    mManager.setOrientation(LinearLayoutManager.VERTICAL);
    setLayoutManager(mManager);

    setOnScrollListener(new OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        adjustPosition(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
      }
    });
  }

  private void adjustPosition(RecyclerView recyclerView, int newState) {
    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
      int i = 0;
      View child = recyclerView.getChildAt(i);
      while (child != null && child.getRight() <= 0) {
        child = recyclerView.getChildAt(++i);
      }
      if (child == null) {
        // The view is no longer visible, just return
        return;
      }
      final int left = child.getLeft();
      final int right = child.getRight();
      final int midpoint = recyclerView.getWidth() / 2;
      if (left < LIST_LEFT_OFFSET) {
        if (right > midpoint) {
          recyclerView.smoothScrollBy(left, 0);
        } else {
          recyclerView.smoothScrollBy(right, 0);
        }
      }
    }
  }

  public void scrollToSelectRow(CalendarDay firstDay, CalendarDay selectDay) {
    int position = DayUtils.calculateMonthPosition(firstDay, selectDay);
    //scrollToPosition(position);
    int rowNum = 0;
    int selectDayRowNum = 0;
    ArrayList<CalendarDay> mDays = createDays(position, firstDay);
    //int height = rowNum *
    int rowHeight = getResources().getDimensionPixelSize(R.dimen.default_month_row_height);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(getResources().getDimension(R.dimen.si_default_text_size));
    Paint.FontMetrics fontMetrics = paint.getFontMetrics();

    float fontHeight = fontMetrics.bottom - fontMetrics.top;
    float y = 0;
    for (int i = 0; i < mDays.size(); i++) {
      CalendarDay calendarDay = mDays.get(i);
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(calendarDay.getTime());
      int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
      if (selectDay.getDayString().equals(calendarDay.getDayString())) {
        y = rowHeight  * rowNum + rowHeight - (rowHeight - fontHeight) / 2;
      }
      if (weekDay == 7) rowNum++;
    }

    mManager.scrollToPositionWithOffset(position, -(int)y);
  }

  private ArrayList<CalendarDay> createDays(int monthPosition, CalendarDay firstDay) {
    ArrayList<CalendarDay> mDays = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(firstDay.getTime());
    int position = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.roll(Calendar.DAY_OF_MONTH, -(position - 1));
    calendar.add(Calendar.MONTH, monthPosition);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int daysNum = DayUtils.getDaysInMonth(month, year);
    for (int i = 0; i < daysNum; i++) {
      mDays.add(new CalendarDay(calendar));
      calendar.roll(Calendar.DAY_OF_MONTH, 1);
    }
    return mDays;
  }

}
