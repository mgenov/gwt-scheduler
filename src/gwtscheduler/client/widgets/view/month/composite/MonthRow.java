package gwtscheduler.client.widgets.view.month.composite;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;

import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Represents a month row.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthRow extends Composite implements WidgetResizeHandler {

  /** impl */
  private FlowPanel impl;
  /** will hold grid data */
  private MonthRowTable grid;
  /** number of cols */
  private final int columns;

  /**
   * Default constructor.
   * @param cols the number of columns
   */
  public MonthRow(int cols) {
    columns = cols;
    impl = new FlowPanel();

    grid = new MonthRowTable(columns);
    impl.add(grid);
    initWidget(impl);

    setStyleName(Resources.monthCss().monthRow());
  }

  public void onResize(WidgetResizeEvent event) {
    if (!isVisible()) {
      return;
    }
    resizeRows();
  }

  /**
   * Resizes the rows.
   */
  public void resizeRows() {
    int availableHeight = getElement().getOffsetHeight();
    grid.redrawRows(availableHeight);
  }

  /**
   * Gets an iterator for the decorable elements.
   * @return the iterator for the decorable elements
   */
  public List<Cell<Element>> getTitleElements() {
    return grid.getTitleElements();
  }

}
