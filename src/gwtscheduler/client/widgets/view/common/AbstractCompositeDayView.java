package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.ViewportPanel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class AbstractCompositeDayView extends Composite {

	/** widget impl */
	protected VerticalPanel impl;

	/**
	 * Default constructor.
	 */
	public AbstractCompositeDayView() {
		impl = new VerticalPanel();

		AbstractDayView dayView = createDayView();

		ViewportPanel vmain = new ViewportPanel();
		vmain.add(dayView, dayView.getResizeHandler());

		Widget topView = createTopView(dayView.getColumns());

		impl.add(topView);
		impl.add(vmain);

		initWidget(impl);
	}

	/**
	 * Creates the top view widget.
	 * 
	 * @param columns the number of columns
	 * @return the top view widget
	 */
	protected abstract Widget createTopView(int columns);

	/**
	 * Creates the day view widget.
	 * 
	 * @return the day view widget
	 */
	protected abstract AbstractDayView createDayView();
}
