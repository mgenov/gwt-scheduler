package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.resize.DefaultResizeHandler;
import gwtscheduler.client.widgets.view.common.WrappedWidget;
import gwtscheduler.client.widgets.view.month.composite.MonthRow;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * View class for months. Handles its own resizes.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class MonthPanel extends WrappedWidget implements WidgetResizeHandler, HasWidgetResizeHandlers, LassoSubject {

  /** Main container */
  private FlowPanel container;
  /** resize handler */
  private DefaultResizeHandler handler;
  /** collection of month rows */
  private List<MonthRow> monthRows;
  /** list of hidden rows */
  private List<MonthRow> hiddenRows;
  /** application config retrieved value */
  private final int WeekSize;

  /**
   * Default constructor.
   */
  public MonthPanel() {
    AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();
    WeekSize = config.daysInWeek();

    container = new FlowPanel();
    handler = new DefaultResizeHandler(this);
    wrapWidget(container);

    monthRows = new ArrayList<MonthRow>();
    hiddenRows = new ArrayList<MonthRow>();

    //6 is an estimate for the necessary rows
    for (int i = 0; i < 6; i++) {
      MonthRow row = new MonthRow(WeekSize);
      monthRows.add(row);
      container.add(row);
    }
  }

  /**
   * Gets the resize handler for this widget.
   * @return the resize handler
   */
  WidgetResizeHandler getWidgetResizeHandler() {
    return this;
  }

  @Override
  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    setRowHeights();
  }

  /**
   * Sets the row height for the month rows.
   */
  protected void setRowHeights() {
    float height = ((float) 100 / monthRows.size());
    for (int i = 0; i < monthRows.size(); i++) {
      Element rowElement = monthRows.get(i).getElement();
      //this is incompatible with 'position:relative'
      // DOM.setStyleAttribute(rowElement, "top", (((float) 100 / monthRows.size()) * i) + "%"); 
      DOM.setStyleAttribute(rowElement, "height", height + "%");
    }
  }

  public void onResize(WidgetResizeEvent event) {
    // we delegate to default handler
    handler.onResize(event);
    for (MonthRow row : monthRows) {
      row.onResize(event);
    }
  }

  /**
   * Unhides a number of rows.
   * @param amount the number of rows to unhide
   */
  void unhideRows(int amount) {
    final int limit = Math.max(0, hiddenRows.size() - amount);
    for (int i = hiddenRows.size() - 1; i >= limit; i--) {
      MonthRow mr = hiddenRows.remove(i);
      mr.setVisible(true);
      monthRows.add(mr);
    }
    setRowHeights();
  }

  /**
   * Hides a given amount of rows
   * @param rowNum the number of rows to hide
   */
  void hideRows(int rowNum) {
    assert rowNum > 0 : "rowNum cannot be negative";
    for (int i = 0; i < rowNum; i++) {
      MonthRow mr = monthRows.remove(monthRows.size() - 1);
      mr.setVisible(false);
      hiddenRows.add(mr);
    }
    resizeAllRows();
  }

  /**
   * Shows only the given amount of rows, hiding the rest.
   * @param rowNum the number of rows to show
   */
  void showRows(int rowNum) {
    while (monthRows.size() < rowNum) {
      MonthRow row = new MonthRow(WeekSize);
      monthRows.add(row);
      container.add(row);
    }
    int diff = monthRows.size() - rowNum;
    if (diff > 0) {
      hideRows(diff);
    } else {
      resizeAllRows();
    }
  }

  /**
   * Resizes all visible rows.
   */
  protected void resizeAllRows() {
    setRowHeights();
    for (MonthRow row : monthRows) {
      row.resizeRows();
    }
  }

  /**
   * Gets the number of visible rows.
   * @return the number of visible rows
   */
  int getVisibleRowsSize() {
    return monthRows.size();
  }

  /**
   * Gets the main cell elements.
   * @return the main cell elements
   */
  List<Cell<Element>> getMainElements() {
    List<Cell<Element>> mergedList = new ArrayList<Cell<Element>>();
    for (MonthRow mr : monthRows) {
      List<Cell<Element>> rl = mr.getTitleElements();
      mergedList.addAll(rl);
    }
    return mergedList;
  }

  @Override
  public int getHeight() {
    return container.getElement().getOffsetHeight();
  }

  @Override
  public int getWidth() {
    return container.getElement().getOffsetWidth();
  }

  @Override
  public int getColNum() {
    //always 7 columns, one for each week day
    return 7; //TODO use config?
  }

  @Override
  public int getRowNum() {
    return getVisibleRowsSize();
  }

  @Override
  public final List<Cell<Element>> getLassoSubjects() {
    return getMainElements();
  }
}
