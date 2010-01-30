package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.view.common.GenericCalendarView;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;
import gwtscheduler.client.widgets.view.common.lasso.LassoAwarePanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class AbstractDaysView extends LassoAwarePanel implements GenericCalendarView, HasMultipleDecorables<Element>,
    HasWidgetRedrawHandlers {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** day view */
  protected AbstractDaysPanel mainPanel;
  /** top view */
  protected Widget topHeader;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** static ref to app configuration */
  private static final AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();

  /**
   * Default constructor.
   */
  public AbstractDaysView() {
    super();
    mainPanel = createDaysPanel();
    topHeader = createTopHeader(mainPanel.getColumns());

    addToWindow(mainPanel);
    addWidgetResizeHandler(mainPanel.getWidgetResizeHandler());

    insert(topHeader, 0);
  }

  @Override
  public LassoAwarePanel asLassoPanel() {
    return this;
  }

  /**
   * Gets the main panel.
   * @return the main panel
   */
  protected AbstractDaysPanel getMainPanel() {
    return mainPanel;
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
  protected void positionLasso(Widget lasso, WidgetResizeEvent event) {
    Element first = getContentDecorableElements().get(0).getCellElement();
    int[] offset = DOMUtils.getOffset(lasso.getParent().getElement(), first);
    if (offset[0] > 0) {
      DOM.setStyleAttribute(lasso.getElement(), "left", offset[0] + "px");
    }
  }

  @Override
  protected void resizeLasso(Widget lasso, WidgetResizeEvent event) {
    lasso.setSize("100%", (config.daysLineHeightEMs() * mainPanel.getRows()) + "em");
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
    g.getCellFormatter().setWidth(0, columns + 2, Constants.SCROLLBAR_WIDTH() + "px");

    topLabels = new ArrayList<Cell<Element>>(columns);
    for (int i = 0; i < columns; i++) {
      Cell<Element> topCell = new BaseCell(0, i);
      topCell.getCellElement().setInnerHTML(0 + ", " + i);//debug

      topLabels.add(topCell);
      g.setWidget(0, 1 + i, DOMUtils.wrapElement(topCell.getCellElement()));
      g.getFlexCellFormatter().setHorizontalAlignment(0, 1 + i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return Collections.unmodifiableList(mainPanel.getTitleDecorables());
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(mainPanel.getMainDecorables());
  }

  public List<Cell<Element>> getMainDecorables() {
    return mainPanel.getMainDecorables();
  }

  /**
   * Creates the day view widget.
   * @return the day view widget
   */
  protected abstract AbstractDaysPanel createDaysPanel();

  @Override
  public HasMultipleDecorables<Element> getDecorables() {
    return this;
  }

  @Override
  public List<Cell<Element>> getVisibleElements() {
    return getContentDecorableElements();
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void startProcessing() {
  }

  @Override
  public void stopProcessing() {
  }

}
