package gwtscheduler.client;

import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTask {

  private String description;
  private Interval interval;
  private String title;

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
}
