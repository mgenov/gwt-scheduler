package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.interfaces.uievents.resize.IHasResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.IWidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFill;
import gwtscheduler.client.widgets.view.common.grid.HorizontalGridFillResizeHandler;

import java.util.Iterator;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Abstract class for day and week views. Holds the main grid cells.
 */
public abstract class AbstractDayPanel extends WrappedWidget implements
    IHasResizeHandler {

  /** Main container */
  protected VerticalPanel container;
  /** Hours grid */
  protected HorizontalGridFill grid;
  /** Resize handler */
  private IWidgetResizeHandler rh;

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
  Iterator<ICell<Element>> getTitleDecorablesIterator() {
    return grid.getTitleElements().iterator();
  }

  /**
   * Gets the iterator for the main decorables.
   * @return the iterator
   */
  Iterator<ICell<Element>> getMainDecorablesIterator() {
    return grid.getMainElements().iterator();
  }

  /**
   * Gets the resize handler for this widget.
   * @return the resize handler.
   */
  public IWidgetResizeHandler getResizeHandler() {
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
