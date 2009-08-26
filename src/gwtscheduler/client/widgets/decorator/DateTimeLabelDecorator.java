package gwtscheduler.client.widgets.decorator;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;

import java.util.List;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.Period;

import com.google.gwt.user.client.Element;

/**
 * Decorator for Days and Weeks.
 * @author malp
 */
public class DateTimeLabelDecorator implements MultipleElementsIntervalDecorator {

  /** used to decide if vertical redraw is needed */
  boolean hasRunVertical = false;

  public void decorate(Interval interval, HasMultipleDecorables<Element> d) {
    int days = interval.toPeriod().toStandardDays().getDays();
    Period p = new Period(0, 0, 0, days, 0, 0, 0, 0);
    Period day = new Period(0, 0, 0, 1, 0, 0, 0, 0);
    decorateHorizontal(interval.getStart(), p, d.getColumnsDecorableElements());
    decorateVertical(interval.getStart(), day, d.getRowsDecorableElements());

  }

  /**
   * Handles vertical labels decoration.
   * @param start the start time
   * @param p the time period that the elems correspond
   * @param elems the decorable elements
   */
  protected void decorateVertical(DateTime start, Period p, List<Cell<Element>> elems) {
    if (hasRunVertical || elems == null) {
      return;
    }
    int hours = p.toStandardHours().getHours();
    int increment = elems.size() / hours;

    assert hours > 0 : "Number of hours should not be <= 0";
    assert increment != 0 : "Increment should not be zero.";

    for (Cell<Element> cell : elems) {
      if (cell.row() % increment == 0) {
        String hour = start.hourOfDay().getAsShortText();
        cell.getCellElement().setInnerText(hour + ":00");
        start = start.plusHours(1);
      }
    }
    hasRunVertical = true;
  }

  /**
   * Handles horizontal decoration.
   * @param start the start time
   * @param p the time period that the elems correspond
   * @param elems the decorable elements
   */
  protected void decorateHorizontal(DateTime start, Period p, List<Cell<Element>> elems) {
    if (elems == null) {
      return;
    }
    int days = p.toStandardDays().getDays();
    int increment = elems.size() / days;

    assert days > 0 : "Number of days should not be <= 0";
    assert increment > 0 : "Increment should not be zero.";

    for (Cell<Element> cell : elems) {
      String wd = start.dayOfWeek().getAsShortText();
      String month = start.monthOfYear().getAsShortText();
      String day = start.dayOfMonth().getAsShortText();

      cell.getCellElement().setInnerText(wd + ", " + month + " " + day);
      start = start.plusDays(increment);
    }
  }
}
