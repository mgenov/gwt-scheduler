package gwtscheduler.client.widgets.common.decorator;

import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;

import java.util.List;
import java.util.Set;

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

  public void decorate(Interval interval,ColumnTitleProvider titleProvider, HasMultipleDecorables<Element> d) {
    Period day = new Period(0, 0, 0, 1, 0, 0, 0, 0);
    decorateHorizontal(titleProvider, d.getColumnsDecorableElements());
    decorateVertical(interval.getStart(), day, d.getRowsDecorableElements());
//              interval.getStart(), p,
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


  protected void decorateHorizontal(ColumnTitleProvider titleProvider, List<Cell<Element>> elems) {
    String columns[] = titleProvider.getColumns(elems.size());
    int index = 0;
    for (Cell<Element> cell : elems) {
      cell.getCellElement().setInnerText(columns[index]);
      index++;
    }
  }
}
