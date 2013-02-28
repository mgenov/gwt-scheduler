package gwtscheduler.client;

import gwtscheduler.common.util.Period;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTask {

  private String description;
  private Period durationInterval;
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

  public Period getDurationInterval() {
    return durationInterval;
  }

  public void setDurationInterval(Period durationInterval) {
    this.durationInterval = durationInterval;
  }

}
