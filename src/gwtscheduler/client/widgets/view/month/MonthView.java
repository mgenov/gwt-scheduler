package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;
import gwtscheduler.client.widgets.view.common.lasso.LassoAwarePanel;
import gwtscheduler.client.widgets.view.common.overlay.EventsPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines the composite month view.
 */
public class MonthView extends LassoAwarePanel implements MonthDisplay, HasMultipleDecorables<Element>, HasWidgetRedrawHandlers {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** month view instance */
  protected MonthPanel monthPanel;
  /** top view cells */
  protected List<Cell<Element>> topLabels;
  /** events panel */
  protected EventsPanel eventsPanel;

  /**
   * Default constructor.
   */
  public MonthView() {
    eventsPanel = new EventsPanel();
    monthPanel = new MonthPanel();
    Widget topHeader = createTopHeader();

    addToWindow(monthPanel);
    addWidgetResizeHandler(monthPanel.getWidgetResizeHandler());

    insert(topHeader, 0);
  }
  

  @Override
  public int getColumns() {
    return monthPanel.getColumns();
  }


  @Override
  public int getRows() {
    return monthPanel.getRows();
  }


  @Override
  protected boolean isOverflowY() {
    return false;
  }

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

      g.setWidget(0, i, DOMUtils.wrapElement(cell.getCellElement()));
      //      g.setElement(0, i, cell.getCellElement());
      g.getCellFormatter().setWidth(0, 0, ((float) 100 / 7) + "%");
      g.getFlexCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(monthPanel.getMainElements());
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return null;
  }

  /**
   * Shows a given number of rows, hiding the others.
   * @param rowNum the number of rows
   */
  public void showRows(int rowNum) {
    monthPanel.showRows(rowNum);
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

  @Override
  public HasMultipleDecorables<Element> getDecorables() {
    return this;
  }

  @Override
  public int getHeight() {
    return monthPanel.getHeight();
  }

  @Override
  public List<Cell<Element>> getVisibleElements() {
    return monthPanel.getMainElements();
  }

  @Override
  public int getWidth() {
    return monthPanel.getWidth();
  }

  @Override
  public int getVisibleRows() {
    return monthPanel.getVisibleRowsSize();
  }

}
