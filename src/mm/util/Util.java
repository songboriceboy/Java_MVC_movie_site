package mm.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {

  public static boolean isDaySame(Date date1, Date date2) {
    return compareDay(date1, date2) == 0;
  }

  public static boolean isDayBefore(Date date1, Date date2) {
    return compareDay(date1, date2) == -1;
  }

  public static boolean isDayAfter(Date date1, Date date2) {
    return compareDay(date1, date2) == 1;
  }

  public static int compareDay(Date date1, Date date2) {
    final Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);
    final Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return -1;
    if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return 1;
    if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return -1;
    if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return 1;
    if (cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR)) return -1;
    if (cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR)) return 1;
    return 0; // same day
  }

  /**
   * Get days between two dates (inclusive). It will generate at least 7 days if the difference
   * is smaller than 7.
   * @param startDate
   * @param endDate can be null
   * @return
   */
  public static List<Date> getDaysInBetween_Inclusive_AtLeast7(Date startDate, Date endDate) {
    List<Date> days = new ArrayList<Date>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);

    int count = 0;
    Date d = calendar.getTime();
    while (count++ < 7 || (endDate != null && d.before(endDate))) {
      days.add(d);
      calendar.add(Calendar.DATE, 1);
      d = calendar.getTime();
    }
    days.add(d);
    return days;
  }

}
