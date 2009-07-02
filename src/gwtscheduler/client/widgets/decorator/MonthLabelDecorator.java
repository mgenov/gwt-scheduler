package gwtscheduler.client.widgets.decorator;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.decoration.MultipleElementsDecorator;

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
public class MonthLabelDecorator implements MultipleElementsDecorator<Element> {
  /** used to decide if horizontal redraw is needed */
  boolean hasRunHorizontal = false;

  public void decorate(Interval interval, HasMultipleDecorables<Element> decorable) {
    Period p = new Period(0, 0, 0, 7, 0, 0, 0, 0);//TODO instead of using hard coded value, use conf
    decorateHorizontal(interval.getStart(), p, decorable.getDaysDecorableElements());
    decorateDays(interval, decorable.getMultipleDecorableElements());
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
      //TODO instead of using hard coded value, use conf
      if (cell.index(7) % increment == 0) {
        cell.getCellElement().setInnerText(start.getDayOfMonth() + "");
        start = start.plusDays(1);
      }
    }
  }
}
