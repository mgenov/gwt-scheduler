package gwtscheduler.client;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.month.CompositeMonthView;
import gwtscheduler.client.widgets.view.month.MonthView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.libideas.client.StyleInjector;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MonthTests implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		StyleInjector.injectStylesheet(Resources.monthCss().getText());

		// months
		MonthView mv = new MonthView();
		ViewportPanel mView = new ViewportPanel();
		mView.add(mv, mv);

		TabPanel main = new DecoratedTabPanel();
		main.add(mView, "Month");
		main.add(new CompositeMonthView(), "CMonth");
		main.selectTab(0);

		RootPanel.get("calendar-main").add(main);
	}
}
