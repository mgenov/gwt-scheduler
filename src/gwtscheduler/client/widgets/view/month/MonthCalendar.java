package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawEvent;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.view.common.RedrawableCalendar;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines the composite month view.
 */
public class MonthCalendar extends RedrawableCalendar implements HasWidgetRedrawHandlers, HasMultipleDecorables<Element> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** month view instance */
  protected MonthPanel monthView;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /**
   * Default constructor.
   */
  public MonthCalendar() {
    monthView = new MonthPanel();
    Widget topHeader = createTopHeader();

    DOM.setStyleAttribute(getWindowPanel().getElement(), "overflowY", "hidden");
    DOM.setStyleAttribute(getWindowPanel().getElement(), "position", "relative");
    getWindowPanel().add(monthView);
    getWindowPanel().addWidgetResizeHandler(monthView.getWidgetResizeHandler());

    getContainer().add(topHeader);
    getContainer().add(getWindowPanel());

    addWidgetRedrawHandler(new WidgetRedrawHandler() {
      @Override
      public void onRedraw(WidgetRedrawEvent widgetRedrawEvent) {
        onSelection();
      }
    });
  }

  /**
   * Debug method.
   */
  void onSelection() {
    DebugUtils.trackPosition(monthView.getElement(), getContentDecorableElements());
  }

  /**
   * Creates the top view.
   * @return the top view widget
   */
  private Widget createTopHeader() {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");

    topLabels = new ArrayList<Cell<Element>>(7);
    for (int i = 0; i < 7; i++) {
      Cell<Element> cell = new BaseCell(0, i);
      cell.getCellElement().setInnerHTML(0 + ", " + i);
      topLabels.add(cell);

      g.setElement(0, i, cell.getCellElement());
      g.getCellFormatter().setWidth(0, 0, ((float) 100 / 7) + "%");
      g.getFlexCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(monthView.getMainElements());
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return null;
  }

  /**
   * Shows a given number of rows, hiding the others.
   * @param rowNum the number of rows
   */
  public void showRows(int rowNum) {
    monthView.showRows(rowNum);
  }

}
