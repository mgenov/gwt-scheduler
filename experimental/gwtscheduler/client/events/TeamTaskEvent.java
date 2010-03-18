package gwtscheduler.client.events;


import gwtscheduler.client.TestTask;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TeamTaskEvent implements Event{
  private TestTask testTask;
  private CalendarColumn column;
  private String id;

  public TeamTaskEvent(TestTask testTask, CalendarColumn column) {
    this.testTask = testTask;
    this.column = column;
    id = System.currentTimeMillis()+"";
  }

  @Override
  public Interval getInterval() {
    return testTask.getInterval();
  }

  @Override
  public void setInterval(Interval interval) {
    testTask.setInterval(interval);
  }

  @Override
  public String getTitle() {
    return testTask.getTitle();
  }

  @Override
  public Object getColumnId() {
    return column.getId();  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getEventId() {
    return id;  
  }
}
