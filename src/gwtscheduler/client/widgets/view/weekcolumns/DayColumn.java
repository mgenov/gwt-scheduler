package gwtscheduler.client.widgets.view.weekcolumns;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

/**
 * Represents a simple date column.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DayColumn implements CalendarColumn {

  private DateTime date;

  /**
   * Default constructor
   * @param date
   */
  public DayColumn(DateTime date) {
    this.date = date;
  }

  /**
   * Constructs the title of the column by its date
   * @return the title
   */
  @Override
  public String getTitle() {
    String title = "";
    if (date != null) {
//      String dayOfWeek = date.dayOfWeek().getAsShortText();
//      String monthOfYear = date.monthOfYear().getAsShortText();
//      String dayOfMonth = date.dayOfMonth().getAsShortText();
//      title = dayOfWeek + ", " + monthOfYear + " " + dayOfMonth;
      int year = date.getYear();
      int month = date.getMonthOfYear();
      int day = date.getDayOfMonth();
      title = day + "/" + month+ "/" + year;
    }
    return title;
  }

  @Override
  public Object getObject() {
    return date;
  }

  @Override
  public void setObject(Object object) {
    date = (DateTime) object;
  }

  @Override
  public boolean isEventForColumn(Event event) {
    Period dayColumnInterval = new Period(date.trimToStart(),date.trimToEnd());
    if (dayColumnInterval.contains(event.getDurationInterval().getStart())) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Object getId() {
    return date;
  }
}
