package com.test.tudou.library.model;

import android.text.TextUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tudou on 15-5-1.
 */
public class CalendarDay {
  public Calendar calendar;

  public int day;
  public int month;
  public int year;

  public CalendarDay() {
    setTime(System.currentTimeMillis());
  }

  public CalendarDay(int year, int month, int day) {
    setDay(year, month - 1, day);
  }

  public CalendarDay(long timeInMillis) {
    setTime(timeInMillis);
  }

  public CalendarDay(Calendar calendar) {
    if (this.calendar == null) {
      this.calendar = calendar;
    }
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);
  }

  private void setTime(long timeInMillis) {
    if (calendar == null) {
      calendar = Calendar.getInstance();
    }
    calendar.setTimeInMillis(timeInMillis);
    month = this.calendar.get(Calendar.MONTH);
    year = this.calendar.get(Calendar.YEAR);
    day = this.calendar.get(Calendar.DAY_OF_MONTH);
  }

  public long getTime() {
    if (calendar == null) {
      calendar = Calendar.getInstance();
    }
    calendar.set(year, month, day);
    return calendar.getTimeInMillis();
  }

  public void set(CalendarDay calendarDay) {
    year = calendarDay.year;
    month = calendarDay.month;
    day = calendarDay.day;
  }

  public void setDay(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
    if (calendar == null) {
      calendar = Calendar.getInstance();
    }
    calendar.set(year, month, day);
  }

  public int getDefaultMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month + 1;
  }

  public void setStringDay(String ymd) {
    if (TextUtils.isEmpty(ymd)) return;
    String[] strings = ymd.split("-");
    year = Integer.valueOf(strings[0]);
    month = Integer.valueOf(strings[1]) - 1;
    day = Integer.valueOf(strings[2]);
    if (calendar == null) {
      calendar = Calendar.getInstance();
    }
    calendar.set(year, month, day);
  }

  public String getDayString() {
    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    return df1.format(new Date(getTime()));
  }
}
