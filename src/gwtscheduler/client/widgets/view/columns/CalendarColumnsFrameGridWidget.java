package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.HasWidgetResizeHandlers;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.CalendarGridPanelWidget;
import gwtscheduler.client.widgets.view.common.HorizontalCalendarViewPanelResizeHandler;

import java.util.Collections;
import java.util.List;

/**
 * Object for columns views. Holds the main grid cells.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarColumnsFrameGridWidget extends Composite implements CalendarColumnsFrameGrid.Display, HasWidgetResizeHandlers, HasMultipleDecorables<Element> {


  /** static ref to css */
  private static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** Main container */
  @UiField
  VerticalPanel container;
  /** Hours grid */
  @UiField
  CalendarGridPanelWidget grid;
  /** root container */
  @UiField
  SimplePanel simplePanel;
  /** Resize handler */
  private WidgetResizeHandler rh;

  /** static ref to app configuration */
//  private static final AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();

  /** ui binder instance */
  private static AbstractDaysPanelUiBinder uiBinder = GWT.create(AbstractDaysPanelUiBinder.class);

  /** ui binder interface */
  interface AbstractDaysPanelUiBinder extends UiBinder<Widget, CalendarColumnsFrameGridWidget> {
  }

  private int rows;
  private int columns;
  private int daysLineHeightEMs;

  /**
   * Default constructor.
   */
  public CalendarColumnsFrameGridWidget(int rows, int columns, int daysLineHeightEMs) {
    this.rows = rows;
    this.columns = columns;
    this.daysLineHeightEMs = daysLineHeightEMs;
    initWidget(uiBinder.createAndBindUi(this));
    rh = new HorizontalCalendarViewPanelResizeHandler(grid);

//    int lh = config.daysLineHeightEMs();
    int lh = this.daysLineHeightEMs;
    container.setSize("100%",rows * lh + "em");
    grid.setSize("100%", rows * lh + "em");
  }

  /**
   * Builds the grid.
   * @return the grid
   */
  @UiFactory
  CalendarGridPanelWidget buildGrid() {
    return new CalendarGridPanelWidget(rows, columns);
  }

  /**
   * Gets the decorable elements.
   * @return the decorable elements
   */
  List<Cell<Element>> getTitleDecorables() {
    return grid.getTitleElements();
  }

  /**
   * Gets the main decorables.
   * @return the decorableelements
   */
  List<Cell<Element>> getMainDecorables() {
    return grid.getMainElements();
  }

  /**
   * Gets the proper resize handler for this widget.
   * @return the resize handler
   */
  @Override
  public WidgetResizeHandler getWidgetResizeHandler() {
    return rh;
  }

  @Override
  public HasMultipleDecorables getDecorables() {
    return this;  
  }

  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
  }

  /**
   * Gets the height in px.
   * @return the height in px
   */
  @Override
  public int getHeight() {
    return grid.getElement().getOffsetHeight();// - CSS.smallBorderPx();
  }

  /**
   * Gets the width in px.
   * @return the width in px
   */
  @Override
  public int getWidth() {
    return grid.getElement().getOffsetWidth() - CSS.titleColumnWidthPx() - CSS.smallPaddingPx();
  }


  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public void removeColumn(int calendarColumnIndex) {
    grid.removeColumn(calendarColumnIndex);
    columns--;
  }

  public void addColumn(String title) {
   grid.addColumn(title);
   columns++;
  }

  @Override
  public List<Cell<Element>> getDecorableElements() {
    return Collections.unmodifiableList(grid.getTitleElements());
  }

  @Override
  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(grid.getMainElements());
  }
}
