package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.ViewportPanel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days.
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
		ViewportPanel viewportPanel = new ViewportPanel();
		viewportPanel.add(dayView, dayView);

		Widget topView = createTopView(dayView.getColumns());

		impl.add(topView);
		impl.add(viewportPanel);
		impl.setSize("100%", "100%");

		initWidget(impl);
	}

	/**
	 * Creates the top view widget.
	 * 
	 * @param columns the number of columns
	 * @return the top view widget
	 */
	protected Widget createTopView(int columns) {
		return new HTMLPanel("<span>blah blah blah <p> other blah </span>");
	}

	/**
	 * Creates the day view widget.
	 * 
	 * @return the day view widget
	 */
	protected abstract AbstractDayView createDayView();
}
