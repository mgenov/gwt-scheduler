package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;

/**
 * Composite class for day views.
 */
public class CompositeDayView extends AbstractCompositeDayView {

	@Override
	protected AbstractDayView createDayView() {
		return new DayView();
	}

}
