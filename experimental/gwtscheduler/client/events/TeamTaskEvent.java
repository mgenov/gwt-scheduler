package gwtscheduler.client.events;


import gwtscheduler.client.TestTask;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.DurationInterval;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.HasColors;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TeamTaskEvent implements Event, HasColors{
  private TestTask testTask;
  private CalendarColumn column;
  private String id;

  public TeamTaskEvent(TestTask testTask, CalendarColumn column) {
    this.testTask = testTask;
    this.column = column;
    id = System.currentTimeMillis()+"";
  }

  @Override
  public DurationInterval getDurationInterval() {
    return testTask.getDurationInterval();
  }

  @Override
  public void setDurationInterval(DurationInterval durationInterval) {
    testTask.setDurationInterval(durationInterval); 
  }

  @Override
  public String getTitle() {
    return testTask.getTitle();
  }

  @Override
  public Object getColumnId() {
    return column.getId();
  }

  @Override
  public String getEventId() {
    return id;  
  }

  @Override
  public String getDescription() {
    return testTask.getDescription();  
  }

  public void setColumn(CalendarColumn column) {
    this.column = column;
  }

  @Override
  public String getHeaderColor() {
    return "#E36666";
  }

  @Override
  public String getBodyColor() {
    return "#FFE0E0";
  }

  @Override
  public String getTitleColor() {
    return "#FFFFFF";
  }

  @Override
  public String getTextColor() {
    return "#000000";
  }
}
