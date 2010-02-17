package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.view.common.EventsPanel;
import gwtscheduler.client.widgets.view.common.LassoAwarePanel2;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class AbstractDaysView extends Composite implements DaysDisplay, HasMultipleDecorables<Element>, HasWidgetRedrawHandlers,
    LassoAwarePanel2.LassoHandler {

  @UiField
  VerticalPanel impl;
  @UiField
  FlexTable header;
  @UiField
  AbstractDaysPanel daysPanel;
  @UiField
  EventsPanel eventsPanel;
  @UiField
  LassoAwarePanel2 lassoAwarePanel;

  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** ui binder instance */
  private static AbstractDaysViewUiBinder uiBinder = GWT.create(AbstractDaysViewUiBinder.class);

  /** ui binder interface */
  interface AbstractDaysViewUiBinder extends UiBinder<Widget, AbstractDaysView> {
  }

  /**
   * Default constructor.
   */
  public AbstractDaysView() {
    initWidget(uiBinder.createAndBindUi(this));
    lassoAwarePanel.addWidgetResizeHandler(daysPanel.getWidgetResizeHandler());
    lassoAwarePanel.setOverflowY(true);
    lassoAwarePanel.setLassoHandler(this);
  }

  /**
   * Creates the top view widget.
   * @return the top view widget
   */
  @UiFactory
  public FlexTable buildHeader() {
    int columns = getColumnsSize();

    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");
    g.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
    g.getCellFormatter().setWidth(0, columns + 2, Constants.SCROLLBAR_WIDTH() + "px");

    topLabels = new ArrayList<Cell<Element>>(columns);

    for (int i = 0; i < columns; i++) {
      Cell<Element> topCell = new BaseCell(0, i);

      //only top row is for labels
      topLabels.add(topCell);

      g.setWidget(0, 1 + i, DOMUtils.wrapElement(topCell.getCellElement()));
      g.getFlexCellFormatter().setHorizontalAlignment(0, 1 + i, HasHorizontalAlignment.ALIGN_CENTER);
    }

    return g;
  }

  /**
   * Creates the day view widget.
   * @return the day view widget
   */
  @UiFactory
  protected abstract AbstractDaysPanel buildDaysPanel();

  /**
   * Gets the number of columns
   * @return the number of cols
   */
  protected abstract int getColumnsSize();

  /**
   * Gets the main panel.
   * @return the main panel
   */
  protected AbstractDaysPanel getMainPanel() {
    return daysPanel;
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
  public void positionLasso(Widget lasso, WidgetResizeEvent event) {
    Element first = getContentDecorableElements().get(0).getCellElement();
    int[] offset = DOMUtils.getOffset(lasso.getParent().getElement(), first);
    if (offset[0] > 0) {
      DOM.setStyleAttribute(lasso.getElement(), "left", offset[0] + "px");
    }
  }

  @Override
  public void resizeLasso(Widget lasso, WidgetResizeEvent event) {
    AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();
    lasso.setSize("100%", (config.daysLineHeightEMs() * daysPanel.getRows()) + "em");
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return Collections.unmodifiableList(daysPanel.getTitleDecorables());
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(daysPanel.getMainDecorables());
  }

  public List<Cell<Element>> getMainDecorables() {
    return daysPanel.getMainDecorables();
  }

  @Override
  public HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler) {
    return lassoAwarePanel.addWidgetRedrawHandler(handler);
  }

  @Override
  public void initLasso(LassoStrategy strat, LassoSubject subject) {
    lassoAwarePanel.initLasso(strat, subject);
  }

  @Override
  public int getColumns() {
    return daysPanel.getColumns();
  }

  @Override
  public int getRows() {
    return daysPanel.getRows();
  }

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
