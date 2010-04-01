package gwtscheduler.client.widgets.view.columns;

import gwtscheduler.client.widgets.view.event.Event;

/**
 * Represents a Simple column in the calendar model
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CalendarColumn {

  /**
   * Gets the title of the column
   * @return  the title
   */
  String getTitle();

  /**
   *
   * @return
   */
  Object getObject();

  /**
   *
   * @param object
   */
  void setObject(Object object);

  /**
   * Checks if an event is form this column
   * @param event - the event
   * @return true if it is, false otherwise
   */
  boolean isEventForColumn(Event event);

  Object getId();

}
