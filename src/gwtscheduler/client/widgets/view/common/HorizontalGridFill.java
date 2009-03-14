package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Horizontal Grid class.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFill extends Composite implements IViewportResizeHandler {

	private static final DayWeekCssResource CSS = Resources.dayWeekCss();

	// private Widget parent;
	private HTMLTable impl;
	private List<Panel> columnWidgets;

	private int columns;
	private int rows;

	/**
	 * Creates a new grid with the supplied dimensions. An additional column
	 * will be added to supply a title.
	 * 
	 * @param rows the number of rows
	 * @param cols the number of columns
	 */
	public HorizontalGridFill(int rows, int cols) {
		impl = new Grid(1, cols + 1);
		impl.setBorderWidth(0);
		impl.setStyleName(CSS.horizontalFillGrid());

		initWidget(impl);

		this.columns = cols;
		this.rows = rows;

		this.columnWidgets = new ArrayList<Panel>();

		// here we add one column for each day
		// one more col for cell labels
		for (int i = 0; i < cols + 1; i++) {
			FlowPanel flowPanel = new FlowPanel();
			String className = null;
			className = (i == 0) ? CSS.titleColumn() : CSS.column();
			flowPanel.setStyleName(className);
			columnWidgets.add(flowPanel);
			impl.setWidget(0, i, flowPanel);
		}

	}

	@Override
	protected void onAttach() {
		super.onAttach();

		// grid construction: first columns, then cells
		Panel titlePanel = columnWidgets.get(0); // first col is title
		for (int r = 0; r < rows; r++) {
			TitleCell title = new TitleCell(r, 0, r + "");
			titlePanel.add(title);
		}

		// regular cells have different size
		for (int c = 1; c < columns + 1; c++) {
			Panel col = columnWidgets.get(c);

			for (int r = 0; r < rows; r++) {
				int id = ((c - 1) * r) + r;
				DayWeekCell cell = new DayWeekCell(r, (c - 1), "cell: " + id);
				col.add(cell);
			}
		}
	}

	/**
	 * Gets the title column width for the title column.
	 * 
	 * @return the title column width
	 */
	private final int getTitleColumnWidth() {
		return CSS.titleColumnWidthPx();
	}

	/**
	 * Gets the title column width for the title column, including borders and
	 * padding.
	 * 
	 * @return the title column offset width
	 */
	private int getTitleColumnOffsetWidth() {
		return getTitleColumnWidth() + CSS.smallPaddingPx();
	}

	public void onViewportResize(ViewportResizeEvent event) {
		Element parentEl = getParent().getElement();
		int width = parentEl.getOffsetWidth();
		int height = parentEl.getOffsetHeight();

		// TODO correct this hack
		if (width <= 0 || height <= 0) {
			return;
		}
		impl.setPixelSize(width, height);
		int[] availableSize = getCellSize(width, height);
		int remainingColWidth = (width - getTitleColumnOffsetWidth()) / columns;

		for (int i = 0; i < columnWidgets.size(); i++) {
			Panel column = columnWidgets.get(i);
			if (i == 0) {
				column.setPixelSize(getTitleColumnOffsetWidth(), height);
			}
			else {
				column.setPixelSize(remainingColWidth, height);
			}

			// column.setSize(getTitleColumnWidth() + "px", h + "px");
			for (Iterator<Widget> it = column.iterator(); it.hasNext();) {
				DayWeekCell cell = (DayWeekCell) it.next();
				if (i == 0) {
					cell.setCompensatedPixelSize(getTitleColumnWidth(), availableSize[1]);
				}
				else {
					cell.setCompensatedPixelSize(availableSize[0], availableSize[1]);
				}
			}
		}
	}

	/**
	 * Gets the default cell size for a non-title cell.
	 * 
	 * @param parentWidth the parent width
	 * @param parentHeight the parent height
	 * @return the cell size, without counting with borders or padding
	 */
	private int[] getCellSize(int parentWidth, int parentHeight) {
		int availW = (parentWidth - getTitleColumnOffsetWidth()) / columns;
		int availH = parentHeight / rows;
		return new int[] { availW, availH };
	}
}
