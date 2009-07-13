package gwtscheduler.client.widgets.view.days;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.WrappedWidget;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFill;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFillResizeHandler;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Abstract class for day and week views. Holds the main grid cells.
 */
public abstract class MultipleDaysPanel extends WrappedWidget implements HasWidgetResizeHandlers {

  /** Main container */
  protected VerticalPanel container;
  /** Hours grid */
  protected HorizontalGridFill grid;
  /** Resize handler */
  private WidgetResizeHandler rh;

  /**
   * Default constructor.
   */
  public MultipleDaysPanel() {
    container = new VerticalPanel();
    container.setSize("100%", getRows() * 2 + "em"); //TODO move this to configuration
    wrapWidget(container);

    grid = new HorizontalGridFill(getRows(), getColumns());
    grid.setSize("100%", getRows() * 2 + "em"); //TODO move this to configuration
    rh = new HorizontalGridFillResizeHandler(grid);

    container.add(grid);
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

  WidgetResizeHandler getWidgetResizeHandler() {
    return rh;
  }

  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
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
