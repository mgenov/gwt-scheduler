package gwtscheduler.client.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import gwtscheduler.client.TestTask;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.DateTime;
import org.goda.time.Interval;

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
  }

  private TestTask testTask;
  private Display display;
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
    this.column = column;
    display.setColumnTitle(this.column.getTitle());
    display.setTitle(task.getTitle());
    Interval interval = task.getInterval();
    DateTime start = interval.getStart();
    DateTime end = interval.getEnd();
    display.setTaskStart(start.getDayOfMonth(),start.getMonthOfYear(),start.getYear(),start.getHourOfDay(),start.getMinuteOfHour());
    display.setTaskEnd(end.getDayOfMonth(),end.getMonthOfYear(),end.getYear(),end.getHourOfDay(),end.getMinuteOfHour());
    display.setDescription(task.getDescription());
  }

  public void show(){
    display.show();
  }

  public HasClickHandlers getOKButton(){
    return display.getOKButton();
  }

  public TestTask getTestTask() {
    return testTask;
  }

  public CalendarColumn getColumn() {
    return column;
  }
}
