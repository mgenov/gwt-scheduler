package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.interfaces.uievents.resize.IWidgetResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.widgets.resize.DefaultResizeHandler;
import gwtscheduler.client.widgets.view.common.WrappedWidget;
import gwtscheduler.client.widgets.view.month.composite.MonthRow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * View class for months.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class MonthPanel extends WrappedWidget implements IWidgetResizeHandler {

  /** Main container */
  private FlowPanel container;
  /** resize handler */
  private DefaultResizeHandler handler;
  /** collection of month rows */
  private List<MonthRow> monthRows;
  /** list of hidden rows */
  private List<MonthRow> hiddenRows;

  /**
   * Default constructor.
   */
  public MonthPanel() {
    container = new FlowPanel();
    handler = new DefaultResizeHandler(this);
    wrapWidget(container);

    monthRows = new ArrayList<MonthRow>();
    hiddenRows = new ArrayList<MonthRow>();

    for (int i = 0; i < 6; i++) {
      MonthRow row = new MonthRow(7);
      monthRows.add(row);
      container.add(row);
    }
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

      float top = ((float) 100 / monthRows.size()) * i;
      Element rowElement = monthRows.get(i).getElement();
      DOM.setStyleAttribute(rowElement, "top", top + "%");
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
   * Hides the last row.
   */
  void hideRow() {
    MonthRow mr = monthRows.remove(monthRows.size() - 1);
    mr.setVisible(false);
    setRowHeights();
    hiddenRows.add(mr);
    for (MonthRow row : monthRows) {
      row.resizeRows();
    }
  }

  /**
   * Gets an iterator for all the decorable elements.
   * @return the iterator
   */
  Iterator<ICell<Element>> getDecorablesIterator() {
    //TODO create a combined iterator
    List<ICell<Element>> mergedList = new ArrayList<ICell<Element>>();
    for (MonthRow mr : monthRows) {
      List<ICell<Element>> rl = mr.getTitleElements();
      mergedList.addAll(rl);
    }
    return mergedList.iterator();
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
   * Gets the main cell elements.
   * @return the main cell elements
   */
  List<ICell<Element>> getMainElements() {
    //TODO: the iterator stuff could be reworked, we don't need the temporary list
    List<ICell<Element>> mergedList = new ArrayList<ICell<Element>>();
    for (MonthRow mr : monthRows) {
      List<ICell<Element>> rl = mr.getTitleElements();
      mergedList.addAll(rl);
    }
    return mergedList;
  }
}
