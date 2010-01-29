package gwtscheduler.client.widgets.view.common.grid;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.view.common.cell.DayCell;
import gwtscheduler.client.widgets.view.common.cell.TitleCell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Horizontal Grid class. The grid has relative CSS positioning. Also contains a
 * title column aligned to the left.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFill extends LazyPanel {

  /** static ref to css */
  private static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** class impl */
  private HTMLTable impl;
  /** title column */
  private Panel titleColumn;
  /** title elements */
  private List<Cell<Element>> titleElements;
  /** columns */
  private List<Panel> mainColumns;
  /** elements for quick access */
  private List<Cell<Element>> mainElements;
  /** grid col count, excluding title column */
  private int columns;
  /** grid row count */
  private int rows;

  /**
   * Creates a new grid with the supplied dimensions. An additional column will
   * be added to supply a title.
   * @param rows the number of rows
   * @param cols the number of columns
   */
  public HorizontalGridFill(int rows, int cols) {
    this.rows = rows;
    this.columns = cols;
    titleElements = new ArrayList<Cell<Element>>();
  }

  @Override
  protected Widget createWidget() {
    impl = new Grid(1, this.columns + 1);
    impl.setBorderWidth(0);
    impl.setStyleName(CSS.horizontalFillGrid());
    impl.getElement().getStyle().setProperty("position", "relative");

    mainColumns = new ArrayList<Panel>();
    mainElements = new ArrayList<Cell<Element>>();

    // here we add one column for each day
    // one more col for cell labels
    for (int i = 0; i < this.columns + 1; i++) {
      FlowPanel flowPanel = new FlowPanel();
      String className = (i == 0) ? CSS.titleColumn() : CSS.column();
      flowPanel.setStyleName(className);
      flowPanel.getElement().getStyle().setProperty("position", "relative");

      if (i == 0) {
        titleColumn = flowPanel;
      } else {
        mainColumns.add(flowPanel);
      }
      impl.setWidget(0, i, flowPanel);
    }

    // create title cells
    for (int r = 0; r < rows; r++) {
      TitleCell title = new TitleCell(r, 0, "");
      title.setWidth(CSS.titleColumnWidthPx() + "px");
      titleColumn.add(title);
      titleElements.add(title);
    }

    // regular cells are different from title cells
    for (int c = 0; c < columns; c++) {
      Panel col = mainColumns.get(c);

      for (int r = 0; r < rows; r++) {
        // int id = (c * r) + c;
        DayCell cell = new DayCell(r, c, "");
        col.add(cell);
        mainElements.add(cell);
      }
    }
    return impl;
  }

  @Override
  protected void onAttach() {
    ensureWidget();
    super.onAttach();
  }

  /**
   * Gets a list of title elements.
   * @return the list of title elements
   */
  public List<Cell<Element>> getTitleElements() {
    return titleElements;
  }

  /**
   * Gets a list of the main elements.
   * @return the main elements
   */
  public List<Cell<Element>> getMainElements() {
    return mainElements;
  }

  /**
   * Gets the title panel column widget.
   * @return the title widget
   */
  public Panel getTitleColumn() {
    return titleColumn;
  }

  /**
   * Gets the main columns widgets.
   * @return the main columns
   */
  public List<Panel> getMainColumns() {
    return mainColumns;
  }

  /**
   * Gets the column count for this grid fill.
   * @return the column count
   */
  int getColumnCount() {
    return columns;
  }

  /**
   * Gets the row count.
   * @return the row count
   */
  int getRowCount() {
    return rows;
  }

}
