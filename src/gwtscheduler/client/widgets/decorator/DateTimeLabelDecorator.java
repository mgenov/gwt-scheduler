package gwtscheduler.client.widgets.decorator;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.interfaces.IDecorator;
import gwtscheduler.client.interfaces.decorable.IHasMultipleDecorables;

import java.util.Iterator;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;

import com.google.gwt.user.client.Element;

/**
 * Decorator for Days and weeks.
 * @author malp
 */
public class DateTimeLabelDecorator implements IDecorator<Element> {

  public void decorate(Interval intv, IHasMultipleDecorables<Element> d) {
    decorateHorizontal(intv, d.getHorizontalDecorableElementsIterator());
    decorateVertical(intv, d.getVerticalDecorableElementsIterator());
    decorateOther(intv, d.getMultipleDecorableElementsIterator());
  }

  /**
   * Handles all elements decoration.
   * @param interval
   * @param it the iterator
   */
  protected void decorateOther(Interval interval, Iterator<ICell<Element>> it) {
    if (it == null) {
      return;
    }
  }

  /**
   * Handles vertical labels decoration.
   * @param interval
   * @param it the iterator
   */
  protected void decorateVertical(Interval intv, Iterator<ICell<Element>> it) {
    if (it == null) {
      return;
    }
    int c = 0;
    MutableDateTime start = intv.getStart().toMutableDateTime();
    while (it.hasNext()) {
      ICell<Element> cell = it.next();
      start.setHourOfDay(c);
      cell.getCellElement().setInnerText(
          start.hourOfDay().getAsShortText() + ":00");
      c++;
    }

  }

  /**
   * Handles horizontal decoration.
   * @param intv
   * @param it the iterator
   */
  protected void decorateHorizontal(Interval intv, Iterator<ICell<Element>> it) {
    if (it == null) {
      return;
    }
    int c = 0;
    DateTime start = intv.getStart();
    while (it.hasNext()) {
      ICell<Element> cell = it.next();
      cell.getCellElement().setInnerText(start.dayOfWeek().getAsShortText());
      start = start.plusDays(1);
      c++;
    }
  }

}
