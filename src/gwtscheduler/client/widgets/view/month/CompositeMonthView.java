package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.events.IWidgetResizeHandler;
import gwtscheduler.client.interfaces.events.WidgetResizeEvent;
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
		final ViewportPanel impl = new ViewportPanel();
		DOM.setStyleAttribute(impl.getElement(), "overflowY", "hidden");
		impl.add(mv, mv);
		initWidget(impl);

		// we'll delegate the resize to the viewport panel
		addHandler(new IWidgetResizeHandler() {
			public void onResize(WidgetResizeEvent event) {
				impl.doDeferredResize();
			}
		}, WidgetResizeEvent.getType());
	}

}
