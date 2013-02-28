package gwtscheduler.client.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import gwtscheduler.client.TestTask;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.util.Period;

import java.util.Date;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTaskDialog {
  public interface Display {

    void setTitle(String title);

    void setTaskStart(int dayOfMonth, int monthOfYear, int year, int hourOfDay, int minuteOfHour);

    void setTaskEnd(int dayOfMonth, int monthOfYear, int year, int hourOfDay, int minuteOfHour);

    void setDescription(String description);

    void show();

    HasClickHandlers getCancelBtn();

    void close();

    HasClickHandlers getOKButton();

    void setColumnTitle(String title);

    String getTaskTitle();

    String getDescription();

    int getEndHour();

    int getStartHour();

    void setTaskStart(String date);

    void setTaskEnd(String date);

    void setStartHour(String time);

    void setEndHour(String time);
  }

  private Display display;
  private TestTask task;
  private CalendarColumn column;

  public TestTaskDialog() {
  }

  public void bindDisplay(final Display display) {
    this.display = display;

    display.getCancelBtn().addClickHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent clickEvent) {
        display.close();
      }
    });

  }

  public void setTestTask(TestTask task, CalendarColumn column){
    this.task = task;
    this.column = column;
    display.setColumnTitle(this.column.getTitle());
    display.setTitle(task.getTitle());
    Period interval = task.getDurationInterval();
    Date start = interval.getStart().asDate();
    Date end = interval.getEnd().asDate();
//    display.setTaskStart(start.getDayOfMonth(),start.getMonthOfYear(),start.getYear(),start.getHourOfDay(),start.getMinuteOfHour());
//    display.setTaskEnd(end.getDayOfMonth(),end.getMonthOfYear(),end.getYear(),end.getHourOfDay(),end.getMinuteOfHour());
    display.setTaskStart(DateTimeFormat.getMediumDateFormat().format(start));
    display.setTaskEnd(DateTimeFormat.getMediumDateFormat().format(end));
    display.setStartHour(DateTimeFormat.getFormat("HH:mm, vvv").format(start));
    display.setEndHour(DateTimeFormat.getFormat("HH:mm, vvv").format(end));
    display.setDescription(task.getDescription());
  }

  public void show(){
    display.show();
  }

  public HasClickHandlers getOKButton(){
    return display.getOKButton();
  }

  public TestTask getTestTask() {
    TestTask t= new TestTask();
    t.setTitle(display.getTaskTitle());
    t.setDurationInterval(task.getDurationInterval());
    t.setDescription(display.getDescription());
    t.setDuration(task.getDuration());
    return t;
  }

  public CalendarColumn getColumn() {
    return column;
  }

  public void close() {
    display.close();
  }
}
