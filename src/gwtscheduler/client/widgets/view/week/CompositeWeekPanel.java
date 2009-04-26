package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;

/**
 * Week view with top week labels.
 */
public class CompositeWeekPanel extends AbstractCompositeDaysPanel {

	@Override
	protected AbstractDayPanel createDayView() {
		return new WeekPanel();
	}

}
