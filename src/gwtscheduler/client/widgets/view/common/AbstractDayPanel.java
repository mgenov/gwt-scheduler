package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.uievents.resize.HasResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFill;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFillResizeHandler;

import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Abstract class for day and week views. Holds the main grid cells.
 */
public abstract class AbstractDayPanel extends WrappedWidget implements HasResizeHandler {

  /** Main container */
  protected VerticalPanel container;
  /** Hours grid */
  protected HorizontalGridFill grid;
  /** Resize handler */
  private WidgetResizeHandler rh;

  /**
   * Default constructor.
   */
  public AbstractDayPanel() {
    container = new VerticalPanel();
    container.setSize("100%", (getRows() * 2) + "em");
    wrapWidget(container);

    grid = new HorizontalGridFill(getRows(), getColumns());
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

  /**
   * Gets the resize handler for this widget.
   * @return the resize handler.
   */
  public WidgetResizeHandler getResizeHandler() {
    return rh;
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
