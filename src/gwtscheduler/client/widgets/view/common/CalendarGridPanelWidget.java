package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.view.common.cell.DayCell;
import gwtscheduler.client.widgets.view.common.cell.TitleCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Grid. The grid has relative CSS positioning. Also contains a
 * time line title column aligned to the left.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarGridPanelWidget extends Composite implements CalendarGridPanel.Display {

  /**
   * static ref to css
   */
  private static final DayWeekCssResource CSS = Resources.dayWeekCss();


  /**
   * class impl
   */
  private FlexTable impl;
  /**
   * title column
   */
  private Panel titleColumn;
  /**
   * title elements
   */
  private List<Cell<Element>> titleElements;
  /**
   * columns
   */
  private List<Panel> mainColumns;
  /**
   * elements for quick access
   */
  private List<Cell<Element>> mainElements;
  /**
   * grid col count, excluding title column
   */
  private int columnsCount;
  /**
   * grid row count
   */
  private int rows;

  /**
   * Creates a new grid with the supplied dimensions. An additional column will
   * be added to supply a title.
   *
   * @param rows the number of rows
   * @param cols the number of columns
   */
  public CalendarGridPanelWidget(int rows, int cols) {
    this.rows = rows;
    this.columnsCount = cols;
    titleElements = new ArrayList<Cell<Element>>();
    impl  = createWidget();
    initWidget(impl);
  }

  protected FlexTable createWidget() {

    impl = new FlexTable();
    impl.setBorderWidth(0);
    impl.setStyleName(CSS.horizontalFillGrid());
    impl.getElement().getStyle().setProperty("position", "relative");

    mainColumns = new ArrayList<Panel>();
    mainElements = new ArrayList<Cell<Element>>();

    // here we makeDraggable one column for each day
    // one more col for cell labels
    for (int i = 0; i < this.columnsCount + 1; i++) {
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
    for (int c = 0; c < columnsCount; c++) {
      Panel col = mainColumns.get(c);

      for (int r = 0; r < rows; r++) {
        DayCell cell = new DayCell(r, c, "");
        col.add(cell);
        mainElements.add(cell);
      }
    }
    return impl;
  }

  /**
   * Gets a list of title elements.
   *
   * @return the list of title elements
   */
  public List<Cell<Element>> getTitleElements() {
    return titleElements;
  }

  /**
   * Gets a list of the main elements.
   *
   * @return the main elements
   */
  public List<Cell<Element>> getMainElements() {
    return mainElements;
  }

  /**
   * Gets the title panel column widget.
   *
   * @return the title widget
   */
  public Panel getTitleColumn() {
    return titleColumn;
  }

  /**
   * Gets the main columns widgets.
   *
   * @return the main columns
   */
  public List<Panel> getMainColumns() {
    return mainColumns;
  }

  /**
   * Gets the column count for this grid fill.
   *
   * @return the column count
   */
  @Override
  public int getColumnCount() {
    return columnsCount;
  }

  /**
   * Gets the row count.
   *
   * @return the row count
   */
  @Override
  public int getRowCount() {
    return rows;
  }

  @Override
  public void removeColumn(int calendarColumnIndex) {
    if (mainColumns.size() - 1 >= 0) {
      impl.remove(mainColumns.get(calendarColumnIndex));
      impl.removeCell(0,calendarColumnIndex+1);
      mainColumns.remove(calendarColumnIndex);
      columnsCount--;
    }
  }

  @Override
  public void addColumn(String title) {
    Panel col = new FlowPanel();
    String className = CSS.column();
    col.setStyleName(className);
    col.getElement().getStyle().setProperty("position", "relative");
    mainColumns.add(col);
    for (int r = 0; r < rows; r++) {
      DayCell cell = new DayCell(r, mainColumns.size(), "");
      col.add(cell);
      mainElements.add(cell);
    }
    impl.setWidget(0, mainColumns.size(), col);

    columnsCount++;
  }
}
