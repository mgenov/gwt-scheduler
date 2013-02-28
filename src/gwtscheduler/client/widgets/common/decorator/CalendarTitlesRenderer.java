package gwtscheduler.client.widgets.common.decorator;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

import java.util.List;

/**
 * Represents title renderer that is responsible for rendering the titles in the header of the calendar(the names of columns)
 * and the hours of the day - depending of the rows that will be visualized.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarTitlesRenderer {


  /**
   * used to decide if vertical redraw is needed
   */
  boolean hasRunVertical = false;

  public CalendarTitlesRenderer() {
  }

  /**
   * Renders the  hours of the day - depending of the rows that will be visualized.
   * @param interval
   * @param elems
   */
  public void renderVerticalTitles(Period interval, List<Cell<Element>> elems) {

    //setting the start of the day
    DateTime start = interval.getStart().trimToStart();

    if (hasRunVertical || elems == null) {
      return;
    }
    //TODO hardCode time
    int hours = 24;
    int increment = elems.size() / hours;

    assert hours > 0 : "Number of hours should not be <= 0";
    assert increment != 0 : "Increment should not be zero.";

    for (Cell<Element> cell : elems) {
      if (cell.row() % increment == 0) {
        String hour = start.hourOfDay() + "";
        cell.getCellElement().setInnerText(hour + ":00");
        start = start.plusHours(1);
      }
    }
    hasRunVertical = true;

  }

  /**
   * Renders the titles in the header of the calendar(the names of columns)
   * @param columns
   * @param elems
   */
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
