package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;
import gwtscheduler.client.widgets.view.common.lasso.LassoAwarePanel;

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
public class MonthCalendar extends LassoAwarePanel implements
    HasWidgetRedrawHandlers, HasMultipleDecorables<Element> {

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
    super();
    monthView = new MonthPanel();
    Widget topHeader = createTopHeader();

    addToWindow(monthView);
    addWidgetResizeHandler(monthView.getWidgetResizeHandler());

    insert(topHeader, 0);

//    addWidgetRedrawHandler(new WidgetRedrawHandler() {
//      @Override
//      public void onRedraw(WidgetRedrawEvent widgetRedrawEvent) {
//        MonthCalendar.this.onRedraw();
//      }
//    });

    initLasso(new HorizontalLassoStrategy(), monthView);
  }

  @Override
  protected void styleWindowPanel(Widget windowPanel) {
    super.styleWindowPanel(windowPanel);
    //TODO this is not elegant, but works...
    DOM.setStyleAttribute(windowPanel.getElement(), "overflowY", "hidden");
  }

//  /**
//   * Debug method.
//   */
//  void onRedraw() {
//  }

  @Override
  protected void positionLasso(Widget lasso, WidgetResizeEvent event) {
    //lasso is fixed position
  }

  @Override
  protected void resizeLasso(Widget lasso, WidgetResizeEvent event) {
    lasso.setPixelSize(event.width, event.height);
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
      g.getFlexCellFormatter().setHorizontalAlignment(0, i,
          HasHorizontalAlignment.ALIGN_CENTER);
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
