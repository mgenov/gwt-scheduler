package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.ViewportPanel;

import com.google.gwt.user.client.ui.Composite;

/**
 * Defines the composite month view.
 */
public class CompositeMonthView extends Composite {

	/**
	 * Default constructor.
	 */
	public CompositeMonthView() {
		MonthView mv = new MonthView();
		ViewportPanel impl = new ViewportPanel();
		impl.add(mv, mv);

		initWidget(impl);
	}

}
