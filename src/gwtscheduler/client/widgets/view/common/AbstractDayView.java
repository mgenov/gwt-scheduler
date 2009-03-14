package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrappedWidget;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Abstract class for day and week views.
 */
public abstract class AbstractDayView extends WrappedWidget implements IViewportResizeHandler {

	/** Main container */
	protected VerticalPanel container;
	/** Hours grid */
	protected HorizontalGridFill grid;

	/**
	 * Default constructor.
	 */
	public AbstractDayView() {
		container = new VerticalPanel();
		container.setSize("100%", "100%");
		wrapWidget(container);

		grid = new HorizontalGridFill(getRows(), getColumns());
		container.add(grid);
	}

	public void onViewportResize(ViewportResizeEvent event) {
		container.setSize("100%", (getRows() * 2) + "em");
		grid.onViewportResize(event);
	}

	/**
	 * Gets the number of rows.
	 * 
	 * @return
	 */
	protected abstract int getRows();

	/**
	 * Gets the number of columns
	 * 
	 * @return
	 */
	protected abstract int getColumns();
}
