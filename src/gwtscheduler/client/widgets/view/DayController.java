package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.widgets.view.day.CompositeDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

@Day
public class DayController extends AbstractViewController<CompositeDayPanel> {

	@Override
	protected CompositeDayPanel createView() {
		return new CompositeDayPanel();
	}

	public String getTabLabel() {
		return "Day";
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
