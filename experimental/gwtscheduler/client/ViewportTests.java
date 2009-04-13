package gwtscheduler.client;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.nav.DateViewsTabPanel;
import gwtscheduler.client.widgets.view.day.CompositeDayView;
import gwtscheduler.client.widgets.view.month.CompositeMonthView;
import gwtscheduler.client.widgets.view.week.CompositeWeekView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Resources.injectAllStylesheets();

		TabPanel main = new DateViewsTabPanel();
		main.add(new CompositeDayView(), "Day");
		main.add(new CompositeWeekView(), "Week");
		main.add(new CompositeMonthView(), "Month");
		main.selectTab(0);

		RootPanel.get("calendar-main").add(main);
	}
}
