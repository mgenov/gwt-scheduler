package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.LassoStrategy;
import gwtscheduler.client.widgets.common.event.HasWidgetRedrawHandlers;
import gwtscheduler.client.widgets.common.event.WidgetRedrawEvent;
import gwtscheduler.client.widgets.common.event.WidgetRedrawHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.LassoAwarePanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the main view of the calendar.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewWidget extends Composite implements CalendarPresenter.Display, HasWidgetRedrawHandlers,
        LassoAwarePanel.LassoHandler, HasWidgets {

  @UiField
  FlowPanel impl;
  
  @UiField
  CalendarHeaderWidget header;

  @UiField
  CalendarContentWidget content;

  /**
   * static ref to css
   */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /**
   * ui binder instance
   */
  private static DayColumnsWidgetUiBinder uiBinder = GWT.create(DayColumnsWidgetUiBinder.class);

  /**
   * ui binder interface
   */
  interface DayColumnsWidgetUiBinder extends UiBinder<Widget, ColumnsViewWidget> {
  }

  private int rows;
  private int columns;
  private int daysLineHeightEMs;
  private int calendarWidth;
  private int calendarHeight;
  private EventBus eventBus;

  /**
   * Default constructor.
   */
  public ColumnsViewWidget(int rows, int columns, int daysLineHeightEMs, int calendarWidth, int calendarHeight, EventBus eventBus) {
    this.rows = rows;
    this.columns = columns;
    this.daysLineHeightEMs = daysLineHeightEMs;
    this.calendarWidth = calendarWidth;
    this.calendarHeight = calendarHeight;
    this.eventBus = eventBus;
    initWidget(uiBinder.createAndBindUi(this));
    content.getEventsPanel().setComplexGrid(this);
    
    content.getLassoAwarePanel().addWidgetResizeHandler(content.getCalendarColumnsFrameGridDisplay().getWidgetResizeHandler());
    content.getLassoAwarePanel().addWidgetResizeHandler(header.getCalendarHeaderResizeHandler());
    content.getLassoAwarePanel().setOverflowY(true);
    content.getLassoAwarePanel().setLassoHandler(this);
  }

  @Override
  public void addWidgetResizeHandler(WidgetResizeHandler handler){
    content.getLassoAwarePanel().addWidgetResizeHandler(handler);
  }

  public void scrollToHour(int hour, int hoursPerDay) {
    content.scrollToHour(hour, hoursPerDay);
  }

  /**
   * Creates the top view widget.
   *
   * @return the top view widget
   */
  @UiFactory
  public CalendarHeaderWidget buildHeader() {
    CalendarHeaderWidget widget = new CalendarHeaderWidget(columns,calendarWidth, eventBus);
    return widget;
  }

  @UiFactory
  public CalendarContentWidget buildContent(){
    return new CalendarContentWidget(rows, columns,daysLineHeightEMs,calendarWidth,calendarHeight);
  }

  /**
   * Creates the day view widget.
   *
   * @return the day view widget
   */
  @Override
  public void forceLayout() {
    content.getLassoAwarePanel().doDeferRedrawResize(new WidgetResizeEvent(calendarWidth,calendarHeight), new WidgetRedrawEvent());
  }


  @Override
  public CalendarHeader.Display getCalendarHeaderDisplay() {
    return header;
  }

  @Override
  public CalendarContent.Display getCalendarContentDisplay() {
    return content;  
  }

  @Override
  public int getHeight() {
    return content.getCalendarColumnsFrameGridDisplay().getHeight();
  }

  @Override
  public int getWidth() {
    return content.getCalendarColumnsFrameGridDisplay().getWidth();
  }

  @Override
  public void forceLayout(Widget lassoPanel, WidgetResizeEvent event) {

    List<Cell<Element>> titles = new ArrayList<Cell<Element>>(content.getCalendarColumnsFrameGridDisplay().getDecorables().getDecorableElements());
    Element title = titles.get(0).getCellElement();
    int offset =title.getAbsoluteRight() - title.getAbsoluteLeft();
    DOM.setStyleAttribute(lassoPanel.getElement(), "left",offset + "px");
    DOM.setStyleAttribute(content.getEventsPanel().getElement(), "left", offset + "px");

    lassoPanel.setSize("100%", (daysLineHeightEMs * content.getCalendarColumnsFrameGridDisplay().getRows()) + "em");
    content.getEventsPanel().setSize("100%", (daysLineHeightEMs * content.getCalendarColumnsFrameGridDisplay().getRows()) + "em");
  }


  @Override
  public HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler) {
    return content.getLassoAwarePanel().addWidgetRedrawHandler(handler);
  }


  @Override
  public void initLasso(LassoStrategy start, ComplexGrid subject) {
    content.getLassoAwarePanel().initLasso(start, subject);
  }

  @Override
  public int getColNum() {
    return content.getCalendarColumnsFrameGridDisplay().getColumns();
  }

  @Override
  public int getRowNum() {
    return  content.getCalendarColumnsFrameGridDisplay().getRows();
  }

  @Override
  public List<Cell<Element>> getVisibleElements() {
    return  content.getCalendarColumnsFrameGridDisplay().getDecorables().getDecorableElements();
  }

  @Override
  public void add(Widget widget) {
    impl.add(widget);
  }

  @Override
  public void clear() {
    impl.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return impl.iterator();
  }

  @Override
  public boolean remove(Widget widget) {
    return impl.remove(widget);
  }
}