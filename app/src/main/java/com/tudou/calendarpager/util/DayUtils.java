package com.tudou.calendarpager.util;

import com.tudou.calendarpager.model.CalendarDay;
import java.util.Calendar;

/**
 * Created by tudou on 15-5-1.
 */
public class DayUtils {

  public final static int WEEKS_IN_YEAR = 12;

  public static int calculateWeekCount(CalendarDay startDay, CalendarDay endDay) {
    /*int years = endDay.year - startDay.year;

    int week = years * WEEKS_IN_YEAR;
    int startWeek = startDay.calendar.WEEK_OF_YEAR;
    int endWeek = endDay.calendar.WEEK_OF_YEAR;
    week -= (startWeek - endWeek);*/

    long x = endDay.getTime() - startDay.getTime();
    int days =(int) x / (1000 * 60 * 60 * 24) + 1;
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(startDay.getTime());
    int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    calendar.setTimeInMillis(endDay.getTime());
    int endDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    int week = days / 7 + 1;
    if (endDayOfWeek < startDayOfWeek) {
      week ++;
    }
    return week;
  }

  public static int getDaysInMonth(int month, int year) {
    switch (month) {
      case Calendar.JANUARY:
      case Calendar.MARCH:
      case Calendar.MAY:
      case Calendar.JULY:
      case Calendar.AUGUST:
      case Calendar.OCTOBER:
      case Calendar.DECEMBER:
        return 31;
      case Calendar.APRIL:
      case Calendar.JUNE:
      case Calendar.SEPTEMBER:
      case Calendar.NOVEMBER:
        return 30;
      case Calendar.FEBRUARY:
        return (year % 4 == 0) ? 29 : 28;
      default:
        throw new IllegalArgumentException("Invalid Month");
    }
  }
}
