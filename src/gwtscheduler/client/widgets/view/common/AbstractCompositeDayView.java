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
	/** viewport widget */
	private ViewportPanel vmain;

	/**
	 * Default constructor.
	 */
	public AbstractCompositeDayView() {
		impl = new VerticalPanel();

		AbstractDayView dayView = createDayView();

		vmain = new ViewportPanel();
		vmain.add(dayView, dayView.getResizeHandler());

		Widget topView = createTopView(dayView.getColumns());

		impl.add(topView);
		impl.add(vmain);

		initWidget(impl);
	}

	/**
	 * Gets the viewport panel.
	 * 
	 * @return the viewport panel
	 */
	protected ViewportPanel getViewportPanel() {
		return vmain;
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
