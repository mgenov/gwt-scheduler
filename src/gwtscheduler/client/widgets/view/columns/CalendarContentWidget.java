package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetRedrawEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.view.common.EventsDashboardView;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.common.LassoAwarePanel;

import java.util.Iterator;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContentWidget extends Composite implements CalendarContent.Display {

  /**
   * ui binder instance
   */
  private static CalendarContentWidgetUiBinder uiBinder = GWT.create(CalendarContentWidgetUiBinder.class);

  /**
   * ui binder interface
   */
  interface CalendarContentWidgetUiBinder extends UiBinder<Widget, CalendarContentWidget> {
  }

  @UiField
  CalendarColumnsFrameGridWidget columnsPanel;
  @UiField
  EventsDashboardView eventsDashboardView;
  @UiField
  LassoAwarePanel lassoAwarePanel;

  
  private int rows;
  private int columns;
  private int daysLineHeightEMs;


  public CalendarContentWidget(int rows, int columns, int daysLineHeightEMs) {
    this.rows = rows;
    this.columns = columns;
    this.daysLineHeightEMs = daysLineHeightEMs;
    initWidget(uiBinder.createAndBindUi(this));
  }

  /**
   * Creates the day view widget.
   *
   * @return the day view widget
   */
  @UiFactory
  public CalendarColumnsFrameGridWidget buildColumnPanel() {
    return new CalendarColumnsFrameGridWidget(rows, columns,daysLineHeightEMs);
  }

  @Override
  public CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay() {
    return columnsPanel;
  }

  @Override
  public void removeColumn(int calendarColumnIndex) {
    columnsPanel.removeColumn(calendarColumnIndex);
  }

  @Override
  public void addColumn(String title) {
    columnsPanel.addColumn(title);
  }

  @Override
  public void fireResizeRedrawEvents() {
    int width = lassoAwarePanel.getOffsetWidth();
    int height = lassoAwarePanel.getOffsetHeight();
    lassoAwarePanel.doDeferRedrawResize(new WidgetResizeEvent(width,height),new WidgetRedrawEvent());
  }

  @Override
  public EventsDashboard.Display getEventsDashboard() {
    return eventsDashboardView;  
  }

  public EventsDashboardView getEventsPanel() {
    return eventsDashboardView;
  }

  public LassoAwarePanel getLassoAwarePanel() {
    return lassoAwarePanel;
  }

  public List<Cell<Element>> getTimeLineDecorables() {
    return columnsPanel.getTitleDecorables();
  }

  @Override
  public HandlerRegistration addDropHandler(DropHandler handler) {
    return addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragInHandler(DragInHandler handler) {
    return addHandler(handler,DragInEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragOutHandler(DragOutHandler handler) {
    return addHandler(handler,DragOutEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
    return addHandler(handler, DragOverEvent.TYPE);
  }
}
