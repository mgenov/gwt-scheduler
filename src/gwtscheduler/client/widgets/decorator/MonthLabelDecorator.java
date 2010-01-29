package gwtscheduler.client.widgets.decorator;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;

import java.util.List;

import org.goda.time.DateTime;
import org.goda.time.Days;
import org.goda.time.Interval;
import org.goda.time.Period;

import com.google.gwt.user.client.Element;

/**
 * Decorator for month labels.
 * @author malp
 */
public class MonthLabelDecorator implements MultipleElementsIntervalDecorator {

  /** used to decide if horizontal redraw is needed */
  boolean hasRunHorizontal = false;
  /** week days */
  private final int WeekDays;

  /**
   * Default constructor.
   */
  public MonthLabelDecorator() {
    WeekDays = AppInjector.GIN.getInjector().getConfiguration().daysInWeek();
  }

  public void decorate(Interval interval, HasMultipleDecorables<Element> decorable) {
    Period p = new Period(0, 0, 0, WeekDays, 0, 0, 0, 0);
    decorateHorizontal(interval.getStart(), p, decorable.getColumnsDecorableElements());
    decorateDays(interval, decorable.getContentDecorableElements());
  }

  /**
   * Decorates the horizontal title label.
   * @param start the start date
   * @param p the period of time
   * @param elems the elements
   */
  private void decorateHorizontal(DateTime start, Period p, List<Cell<Element>> elems) {
    if (elems == null || hasRunHorizontal) {
      return;
    }
    int days = p.toStandardDays().getDays();
    int increment = elems.size() / days;

    assert days > 0 : "Number of days should not be <= 0";
    assert increment > 0 : "Increment should not be zero.";

    for (Cell<Element> cell : elems) {
      cell.getCellElement().setInnerText(start.dayOfWeek().getAsShortText());
      start = start.plusDays(increment);
    }
    hasRunHorizontal = true;
  }

  /**
   * Decorates all the cells.
   * @param interval the interval
   * @param elems the elements
   */
  private void decorateDays(Interval interval, List<Cell<Element>> elems) {
    if (elems == null) {
      return;
    }
    Days days = Days.daysBetween(interval.getStart(), interval.getEnd());
    int increment = elems.size() / days.getDays();

    assert days.getDays() > 0 : "Number of days should not be <= 0";
    assert increment > 0 : "Increment should not be zero.";

    DateTime start = interval.getStart();
    for (Cell<Element> cell : elems) {

      if (cell.index(WeekDays) % increment == 0) {
        String label = "";
        if (start.getDayOfMonth() == 1) {
          label = start.monthOfYear().getAsShortText() + ", ";
        }
        label = label + start.getDayOfMonth();
        cell.getCellElement().setInnerText(label);
        start = start.plusDays(1);
      }
    }
  }
}
