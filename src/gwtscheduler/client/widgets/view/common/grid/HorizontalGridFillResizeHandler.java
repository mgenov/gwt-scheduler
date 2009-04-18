package gwtscheduler.client.widgets.view.common.grid;

import gwtscheduler.client.interfaces.events.IResizeHandler;
import gwtscheduler.client.interfaces.events.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.resize.AbstractResizeHandler;
import gwtscheduler.client.widgets.view.common.cell.DayWeekCell;

import java.util.Iterator;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Handlers resize events for the wrapper widget.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFillResizeHandler extends AbstractResizeHandler<HorizontalGridFill>
		implements IResizeHandler {

	/** static ref to css */
	private static final DayWeekCssResource CSS = Resources.dayWeekCss();

	/**
	 * Creates a new resize handler for the supplied widget.
	 * 
	 * @param widget the widget thath should handle resizes
	 */
	public HorizontalGridFillResizeHandler(HorizontalGridFill widget) {
		super(widget);
	}

	@Override
	protected void onDelayedResize(WidgetResizeEvent event) {
		onResize(event);
	}

	/**
	 * Called when a viewport resize event ocurred.
	 * 
	 * @param event the resize event
	 */
	@Override
	public void onResize(WidgetResizeEvent event) {
		super.onResize(event);

		HorizontalGridFill grid = getTarget();
		Element parentEl = grid.getParent().getElement();
		int height = parentEl.getOffsetHeight();
		int width = event.width;

		if (width <= 0 || height <= 0) {
			return;
		}

		grid.setPixelSize(width - Constants.SCROLLBAR_WIDTH, height);
		int[] availableSize = getCellSize(width - Constants.SCROLLBAR_WIDTH, height);

		// we only need to set cell widths, not col widths

		// int remainW = width - getTitleColumnOffsetWidth();
		// int remainingColWidth = (remainW / grid.getColumnCount()) -
		// Constants.SCROLLBAR_WIDTH;

		for (int i = 0; i < grid.getColumnWidgets().size(); i++) {
			Panel column = grid.getColumnWidgets().get(i);
			// if (i == 0) {
			// column.setPixelSize(getTitleColumnOffsetWidth(), height);
			// } else {
			// column.setPixelSize(remainingColWidth, height);
			// }

			// resize cells
			for (Iterator<Widget> it = column.iterator(); it.hasNext();) {
				DayWeekCell cell = (DayWeekCell) it.next();
				if (i == 0) {
					cell.setCompensatedPixelSize(getTitleColumnWidth(), availableSize[1]);
				} else {
					cell.setCompensatedPixelSize(availableSize[0], availableSize[1]);
				}
			}
		}
	}

	/**
	 * Gets the title column width for the title column.
	 * 
	 * @return the title column width
	 */
	final int getTitleColumnWidth() {
		return CSS.titleColumnWidthPx();
	}

	/**
	 * Gets the title column width for the title column, including borders and
	 * padding.
	 * 
	 * @return the title column offset width
	 */
	int getTitleColumnOffsetWidth() {
		return getTitleColumnWidth();// + CSS.smallPaddingPx();
	}

	/**
	 * Gets the default cell size for a non-title cell.
	 * 
	 * @param parentWidth the parent width
	 * @param parentHeight the parent height
	 * @return the cell size, without counting with borders or padding
	 */
	int[] getCellSize(int parentWidth, int parentHeight) {
		int availW = ((parentWidth - getTitleColumnOffsetWidth()) / getTarget().getColumnCount());
		int availH = parentHeight / getTarget().getRowCount();
		return new int[] { availW, availH };
	}
}
