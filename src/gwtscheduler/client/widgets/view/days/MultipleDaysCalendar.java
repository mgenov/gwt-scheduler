package gwtscheduler.client.widgets.view.days;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
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
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class MultipleDaysCalendar extends LassoAwarePanel implements
    HasWidgetRedrawHandlers, HasMultipleDecorables<Element> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** day view */
  protected MultipleDaysPanel mainView;
  /** top view */
  protected Widget topHeader;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** static ref to app configuration */
  private static final AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();

  /**
   * Default constructor.
   */
  public MultipleDaysCalendar() {
    super();
    mainView = createDaysPanel();
    topHeader = createTopHeader(mainView.getColumns());

    addToWindow(mainView);
    addWidgetResizeHandler(mainView.getWidgetResizeHandler());

    insert(topHeader, 0);

//    addWidgetRedrawHandler(new WidgetRedrawHandler() {
//      @Override
//      public void onRedraw(WidgetRedrawEvent widgetRedrawEvent) {
//        MultipleDaysCalendar.this.onRedraw();
//      }
//    });

    initLasso(getStrategy(), mainView);
  }

  /**
   * Gets the lasso strategy
   * @return the strategy
   */
  protected abstract LassoStrategy getStrategy();

//  /**
//   * Utility method fired when the calendar is redrawn.
//   */
//  void onRedraw() {
//  }

  @Override
  protected void positionLasso(Widget lasso, WidgetResizeEvent event) {
    Element first = getContentDecorableElements().get(0).getCellElement();
    int[] offset = DOMUtils.getOffset(lasso.getParent().getElement(), first);
    if (offset[0] > 0) {
      DOM.setStyleAttribute(lasso.getElement(), "left", offset[0] + "px");
    }
  }

  @Override
  protected void resizeLasso(Widget lasso, WidgetResizeEvent event) {
    lasso.setSize("100%", (config.daysLineHeightEMs() * mainView.getRows())
        + "em");
  }

  /**
   * Creates the top view widget.
   * @param columns the number of columns
   * @return the top view widget
   */
  protected Widget createTopHeader(int columns) {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");
    g.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
    g.getCellFormatter().setWidth(0, columns + 2,
        Constants.SCROLLBAR_WIDTH + "px");

    topLabels = new ArrayList<Cell<Element>>(columns);
    for (int i = 0; i < columns; i++) {
      Cell<Element> topCell = new BaseCell(0, i);
      topCell.getCellElement().setInnerHTML(0 + ", " + i);//debug

      topLabels.add(topCell);
      g.setElement(0, 1 + i, topCell.getCellElement());
      g.getFlexCellFormatter().setHorizontalAlignment(0, 1 + i,
          HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return Collections.unmodifiableList(mainView.getTitleDecorables());
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(mainView.getMainDecorables());
  }

  /**
   * Creates the day view widget.
   * @return the day view widget
   */
  protected abstract MultipleDaysPanel createDaysPanel();
}
