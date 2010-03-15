package gwtscheduler.client;

import com.google.gwt.dev.shell.BrowserChannel;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.Event;
import org.goda.time.Hours;
import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTask {

  private String description;
  private Interval interval;
  private String title;
  private Integer duration;

  public TestTask() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Integer getDuration() {
    return duration;
  }
}
