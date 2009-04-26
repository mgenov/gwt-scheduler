package gwtscheduler.client;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.nav.DateViewsTabPanel;
import gwtscheduler.client.widgets.view.day.CompositeDayPanel;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.client.widgets.view.week.CompositeWeekPanel;

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
		main.add(new CompositeDayPanel(), "Day");
		main.add(new CompositeWeekPanel(), "Week");
		main.add(new CompositeMonthPanel(), "Month");
		main.selectTab(0);

		RootPanel.get("calendar-main").add(main);
	}
}
