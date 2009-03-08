package gwtscheduler.client;

import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.day.DayView;
import gwtscheduler.client.widgets.view.month.MonthView;
import gwtscheduler.client.widgets.view.week.WeekView;

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
        // days
        DayView dv = new DayView();
        ViewportPanel dView = new ViewportPanel();
        dView.add(dv, dv);

        // week
        WeekView wv = new WeekView();
        ViewportPanel wView = new ViewportPanel();
        wView.add(wv, wv);

        // months
        MonthView mv = new MonthView();
        ViewportPanel mView = new ViewportPanel();
        mView.add(mv, mv);

        TabPanel main = new TabPanel();
        main.add(dView, "Day");
        main.add(wView, "Week");
        main.add(mView, "Month");
        main.selectTab(0);
        RootPanel.get("calendar-main").add(main);
    }
}
