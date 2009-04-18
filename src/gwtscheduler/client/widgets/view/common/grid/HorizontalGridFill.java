package gwtscheduler.client.widgets.view.common.grid;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.view.common.cell.DayWeekCell;
import gwtscheduler.client.widgets.view.common.cell.TitleCell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Horizontal Grid class.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFill extends LazyPanel {

	/** static ref to css */
	private static final DayWeekCssResource CSS = Resources.dayWeekCss();

	/** class impl */
	private HTMLTable impl;
	/** columns */
	private List<Panel> columnWidgets;
	/** grid col count, excluding title column */
	private int columns;
	/** grid row count */
	private int rows;

	/**
	 * Creates a new grid with the supplied dimensions. An additional column will
	 * be added to supply a title.
	 * 
	 * @param rows the number of rows
	 * @param cols the number of columns
	 */
	public HorizontalGridFill(int rows, int cols) {
		this.rows = rows;
		this.columns = cols;
	}

	@Override
	protected Widget createWidget() {
		impl = new Grid(1, this.columns + 1);
		impl.setBorderWidth(0);
		impl.setStyleName(CSS.horizontalFillGrid());

		columnWidgets = new ArrayList<Panel>();

		// here we add one column for each day
		// one more col for cell labels
		for (int i = 0; i < this.columns + 1; i++) {
			FlowPanel flowPanel = new FlowPanel();
			String className = null;
			className = (i == 0) ? CSS.titleColumn() : CSS.column();
			flowPanel.setStyleName(className);
			columnWidgets.add(flowPanel);
			impl.setWidget(0, i, flowPanel);
		}

		// create title cells
		Panel titlePanel = columnWidgets.get(0); // first col is title
		for (int r = 0; r < rows; r++) {
			TitleCell title = new TitleCell(r, 0, r + "");
			title.setWidth(CSS.titleColumnWidthPx() + "px");
			titlePanel.add(title);
		}

		// regular cells are different from title cells
		for (int c = 1; c < columns + 1; c++) {
			Panel col = columnWidgets.get(c);

			for (int r = 0; r < rows; r++) {
				int id = ((c - 1) * r) + c;
				DayWeekCell cell = new DayWeekCell(r, (c - 1), "cell: " + id);
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
	 * Gets the column widgets list.
	 * 
	 * @return the column widgets list
	 */
	public List<Panel> getColumnWidgets() {
		return columnWidgets;
	}

	/**
	 * Gets the column count for this grid fill.
	 * 
	 * @return the column count
	 */
	int getColumnCount() {
		return columns;
	}

	/**
	 * Gets the row count.
	 * 
	 * @return the row count
	 */
	int getRowCount() {
		return rows;
	}
}
