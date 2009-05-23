package gwtscheduler.client.widgets.view.common.grid;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.view.common.cell.DayWeekCell;
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
 * Horizontal Grid class.
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
  private List<ICell<Element>> titleElements;
  /** columns */
  private List<Panel> mainColumns;
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
    titleElements = new ArrayList<ICell<Element>>();
  }

  @Override
  protected Widget createWidget() {
    impl = new Grid(1, this.columns + 1);
    impl.setBorderWidth(0);
    impl.setStyleName(CSS.horizontalFillGrid());

    mainColumns = new ArrayList<Panel>();

    // here we add one column for each day
    // one more col for cell labels
    for (int i = 0; i < this.columns + 1; i++) {
      FlowPanel flowPanel = new FlowPanel();
      String className = (i == 0) ? CSS.titleColumn() : CSS.column();
      flowPanel.setStyleName(className);
      if (i == 0) {
        titleColumn = flowPanel;
      } else {
        mainColumns.add(flowPanel);
      }
      impl.setWidget(0, i, flowPanel);
    }

    // create title cells
    for (int r = 0; r < rows; r++) {
      // TODO: apply decoration here
      TitleCell title = new TitleCell(r, 0, r + "");
      title.setWidth(CSS.titleColumnWidthPx() + "px");
      titleColumn.add(title);
      titleElements.add(title);
    }

    // regular cells are different from title cells
    for (int c = 0; c < columns; c++) {
      Panel col = mainColumns.get(c);

      for (int r = 0; r < rows; r++) {
        int id = (c * r) + c;
        // TODO: apply decoration here
        DayWeekCell cell = new DayWeekCell(r, c, "cell: " + id);
        col.add(cell);
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
  public List<ICell<Element>> getTitleElements() {
    return titleElements;
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
