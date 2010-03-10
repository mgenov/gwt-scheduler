package gwtscheduler.client.widgets.view.columns;

import gwtscheduler.common.event.Event;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CalendarColumn {

  String getTitle();

  Object getObject();

  void setObject(Object object);

  boolean isEventForColumn(Event event);

}
