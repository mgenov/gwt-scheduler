package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.widgets.view.week.CompositeWeekPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

/**
 * Week controller for week views.
 * 
 * @author malp
 */
@Week
public class WeekController extends AbstractViewController<CompositeWeekPanel> {

	@Override
	protected CompositeWeekPanel createView() {
		return new CompositeWeekPanel();
	}

	public String getTabLabel() {
		return "Week";
	}

	public ITimePeriod onNavigateNext() {
		return null;
	}

	public ITimePeriod onNavigatePrevious() {
		return null;
	}

	public void onNavigateTo(IDate date) {
	}

}
