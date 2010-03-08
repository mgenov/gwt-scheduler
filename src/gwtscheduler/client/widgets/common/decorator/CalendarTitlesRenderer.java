package gwtscheduler.client.widgets.common.decorator;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.Period;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarTitlesRenderer {


  /**
   * used to decide if vertical redraw is needed
   */
  boolean hasRunVertical = false;

  public CalendarTitlesRenderer() {
  }

  public void renderVerticalTitles(Interval interval, List<Cell<Element>> elems) {

    Period p = new Period(0, 0, 0, 1, 0, 0, 0, 0);
    DateTime start = interval.getStart();

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

  public void renderHorizontalTitles(List<CalendarColumn> columns, List<Cell<Element>> elems) {
    if (columns == null || elems == null) {
      return;
    }
    assert columns.size() == elems.size() : "Columns number is not the same as cells!";

    int index = 0;
    for (Cell<Element> cell : elems) {
      cell.getCellElement().setInnerText(columns.get(index).getTitle());
      index++;
    }

  }
}
