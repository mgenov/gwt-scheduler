package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.LassoStrategy;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.HasWidgetRedrawHandlers;
import gwtscheduler.client.widgets.common.event.WidgetRedrawEvent;
import gwtscheduler.client.widgets.common.event.WidgetRedrawHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.EventsPanel;
import gwtscheduler.client.widgets.view.common.LassoAwarePanel;

import java.util.Collections;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewWidget extends Composite implements CalendarPresenter.Display, HasMultipleDecorables<Element>, HasWidgetRedrawHandlers,
        LassoAwarePanel.LassoHandler {

  @UiField
  VerticalPanel impl;
  @UiField
//  FlexTable header;
  CalendarHeaderWidget header;

  @UiField
  CalendarColumnsFrameGridWidget columnsPanel;
  @UiField
  EventsPanel eventsPanel;
  @UiField
  LassoAwarePanel lassoAwarePanel;

  /**
   * top view cells
   */
//  protected List<Cell<Element>> topLabels;

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

  /**
   * Default constructor.
   */
  public ColumnsViewWidget(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    initWidget(uiBinder.createAndBindUi(this));
    eventsPanel.setComplexGrid(this);
    lassoAwarePanel.addWidgetResizeHandler(columnsPanel.getWidgetResizeHandler());
    lassoAwarePanel.setOverflowY(true);
    lassoAwarePanel.setLassoHandler(this);
  }

  /**
   * Creates the top view widget.
   *
   * @return the top view widget
   */
  @UiFactory
  public CalendarHeaderWidget buildHeader() {
    CalendarHeaderWidget widget = new CalendarHeaderWidget(columns);
    return widget;
  }

  /**
   * Creates the day view widget.
   *
   * @return the day view widget
   */
  @UiFactory
  public CalendarColumnsFrameGridWidget buildColumnPanel() {
    return new CalendarColumnsFrameGridWidget(rows, columns);
  }

  @Override
  public void forceLayout() {
    lassoAwarePanel.doDeferRedrawResize(new WidgetResizeEvent(), new WidgetRedrawEvent());
  }

  /**
   * Gets the main panel.
   *
   * @return the main panel
   */
  public CalendarColumnsFrameGrid.Display getMainPanel() {
    return columnsPanel;
  }

  @Override
  public void removeColumnHeader(int calendarColumnIndex) {
    columnsPanel.removeColumn(calendarColumnIndex);
//    header.removeCell(calendarColumnIndex + 1);
  }

  @Override
  public void addColumn(String title) {
//    columns++;
    columnsPanel.addColumn(title);
//    header.addColumn(title);
  }

//  @Override
//  public WidgetResizeHandler getCalendarHeaderResizeHandler() {
//    return header;
//  }

  @Override
  public CalendarHeader.Display getCalendarHeaderDisplay() {
    return header;
  }

  @Override
  public int getHeight() {
    return getMainPanel().getHeight();
  }

  @Override
  public int getWidth() {
    return getMainPanel().getWidth();
  }

  @Override
  public void forceLayout(Widget lassoPanel, WidgetResizeEvent event) {
    Element first = getContentDecorableElements().get(0).getCellElement();
    int[] offset = DOMUtils.getOffset(lassoPanel.getParent().getElement(), first);
    if (offset[0] > 0) {
      DOM.setStyleAttribute(lassoPanel.getElement(), "left", offset[0] + "px");
      DOM.setStyleAttribute(eventsPanel.getElement(), "left", offset[0] + "px");
    }

    AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();
    lassoPanel.setSize("100%", (config.daysLineHeightEMs() * columnsPanel.getRows()) + "em");
    eventsPanel.setSize("100%", (config.daysLineHeightEMs() * columnsPanel.getRows()) + "em");
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(header.getTopLabels());
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return Collections.unmodifiableList(columnsPanel.getTitleDecorables());
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(columnsPanel.getMainDecorables());
  }

  public List<Cell<Element>> getMainDecorables() {
    return columnsPanel.getMainDecorables();
  }

  @Override
  public HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler) {
    return lassoAwarePanel.addWidgetRedrawHandler(handler);
  }


  @Override
  public void initLasso(LassoStrategy start, ComplexGrid subject) {
    lassoAwarePanel.initLasso(start, subject);
  }

  @Override
  public int getColNum() {
    return columnsPanel.getColumns();
  }

  @Override
  public int getRowNum() {
    return columnsPanel.getRows();
  }

  @Override
  public HasMultipleDecorables<Element> getDecorables() {
    return this;
  }

  @Override
  public List<Cell<Element>> getVisibleElements() {
    return getContentDecorableElements();
  }


}