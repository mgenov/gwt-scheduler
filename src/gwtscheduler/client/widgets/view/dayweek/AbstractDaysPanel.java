package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;
import gwtscheduler.client.widgets.view.common.HorizontalGridFillResizeHandler;

import java.util.List;

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

/**
 * Abstract class for day and week views. Holds the main grid cells.
 */
public abstract class AbstractDaysPanel extends Composite implements HasWidgetResizeHandlers {

  /** static ref to css */
  private static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** Main container */
  @UiField
  VerticalPanel container;
  /** Hours grid */
  @UiField
  HorizontalGridFill grid;
  /** root container */
  @UiField
  SimplePanel simplePanel;
  /** Resize handler */
  private WidgetResizeHandler rh;

  /** static ref to app configuration */
  private static final AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();

  /** ui binder instance */
  private static AbstractDaysPanelUiBinder uiBinder = GWT.create(AbstractDaysPanelUiBinder.class);

  /** ui binder interface */
  interface AbstractDaysPanelUiBinder extends UiBinder<Widget, AbstractDaysPanel> {
  }

  /**
   * Default constructor.
   */
  public AbstractDaysPanel() {
    initWidget(uiBinder.createAndBindUi(this));
    rh = new HorizontalGridFillResizeHandler(grid);

    int lh = config.daysLineHeightEMs();
    container.setSize("100%", getRows() * lh + "em");
    grid.setSize("100%", getRows() * lh + "em");
  }

  /**
   * Builds the grid.
   * @return the gri
   */
  @UiFactory
  HorizontalGridFill buildGrid() { // method name is insignificant
    return new HorizontalGridFill(getRows(), getColumns());
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
  WidgetResizeHandler getWidgetResizeHandler() {
    return rh;
  }

  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
  }

  /**
   * Gets the height in px.
   * @return the height in px
   */
  int getHeight() {
    return grid.getElement().getOffsetHeight();// - CSS.smallBorderPx();
  }

  /**
   * Gets the width in px.
   * @return the width in px
   */
  int getWidth() {
    return grid.getElement().getOffsetWidth() - CSS.titleColumnWidthPx() - CSS.smallPaddingPx();
  }

  /**
   * Gets the number of rows.
   * @return the number of rows of this panel
   */
  protected abstract int getRows();

  /**
   * Gets the number of columns.
   * @return the number of columns of this panel
   */
  protected abstract int getColumns();

}
