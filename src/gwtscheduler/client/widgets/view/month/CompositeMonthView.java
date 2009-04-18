package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.ViewportPanel;

import com.google.gwt.user.client.DOM;
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
		DOM.setStyleAttribute(impl.getElement(), "overfloyY", "hidden");
		impl.add(mv, mv);
		initWidget(impl);
	}

	@Override
	public void setVisible(boolean visible) {
		// triggers resize
		super.setVisible(visible);
		getWidget().setVisible(visible);
	}

}
