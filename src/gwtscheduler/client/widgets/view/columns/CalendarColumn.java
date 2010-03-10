package gwtscheduler.client.widgets.view.columns;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CalendarColumn {

  String getTitle();

  Object getObject();

  void setObject(Object object);

  boolean isEventForColumn();

}
