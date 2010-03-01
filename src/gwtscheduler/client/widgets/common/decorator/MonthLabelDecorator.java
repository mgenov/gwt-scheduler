package gwtscheduler.client.widgets.common.decorator;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;

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

  public void decorate(Interval interval,ColumnTitleProvider columnTitleProvider, HasMultipleDecorables<Element> decorable) {
    decorateHorizontal(columnTitleProvider, decorable.getColumnsDecorableElements());
    decorateDays(interval, decorable.getContentDecorableElements());
  }


  /**
   * Decorates the horizontal title label.
   * @param titleProvider provides the titles fpr the columns
   * @param elems the elements
   */
  private void decorateHorizontal(ColumnTitleProvider titleProvider, List<Cell<Element>> elems) {
    if (elems == null || hasRunHorizontal) {
      return;
    }
    String columns[] = titleProvider.getColumns(elems.size());
    int index = 0;
    for (Cell<Element> cell : elems) {
      cell.getCellElement().setInnerText(columns[index]);
      index++;
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
