package gwtscheduler.tests.gwt;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;

import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationIntervaUpdateHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.Event;
import org.goda.time.DateTime;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableInterval;

/**
 * Utility class for lasso tests.
 * @author malp
 */
public class DateTimeAwarePresenter implements ComplexGrid,CalendarPresenter {

  final int rows, cols;

  static Interval Interval;

  static {
    DateTime dt1 = new DateTime(2009, 01, 01, 01, 01, 01, 01);
    DateTime dt2 = new DateTime(2009, 01, 31, 01, 01, 01, 01);
    Interval = new Interval(dt1, dt2);
  }

  /**
   * Creates a new mock lasso subject, with a 100px x 100px grid.
   * @param rows the number of rows
   * @param cols the number of cols
   */
  public DateTimeAwarePresenter(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public int getColNum() {
    return cols;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public int getWidth() {
    return 100;
  }

  @Override
  public int getHeight() {
    return 100;
  }

//  @Override
//  public Interval getIntervalForRange(int[] start, int[] end) {
//    Instant from = getInstantForCell(start);
//    Instant to = getInstantForCell(end);
//    //ranges are closed on start and open on end
//    //[start, end[
//    // so a correction is needed
//    MutableDateTime last = to.toMutableDateTime();
//    last.addDays(1);
//    Interval i = new Interval(from, last.toInstant());
//    return i;
//  }


  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = Interval.toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();

    time.addDays(distance);
    return time.toInstant();
  }

  @Override
  public void deleteColumn(CalendarColumn column) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addColumn(CalendarColumn column) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HandlerRegistration addCalendarDropHandler(CalendarDropHandler calendarDropHandler) {
    return null;
  }

  @Override
  public HandlerRegistration addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    return null;
  }

  @Override
  public void setCalendarType(CalendarType type) {
  }

  @Override
  public CalendarType getCalendarType() {
    return null;
  }

  @Override
  public void addCalendarEvent(Event event) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HandlerRegistration addEventResizeEndHandler(CalendarEventDurationIntervaUpdateHandler handler) {
    return null;
  }

  @Override
  public HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    return null;
  }

  @Override
  public void updateEvent(Event event) {
  }

  @Override
  public void navigateToDateTime(DateTime dateTime) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setEnable(boolean enable) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }


  @Override
  public void deleteEvent(Event event) {
    //To change body of implemented methods use File | Settings | File Templates.
  }


  @Override
  public void forceLayout() {
  }

//  @Override
//  public EventNavigationListener getNavigationListener() {
//    return null;
//  }

  @Override
  public void bindDisplay(Display display) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

//  @Override
//  public void setColNum(int columns) {
//    //To change body of implemented methods use File | Settings | File Templates.
//  }

  @Override
  public void setTittle(String tabLabel) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Display getDisplay() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getTitle() {
    return null;
  }

//  @Override
//  public Widget getWidgetDisplay() {
//    return null;
//  }
}