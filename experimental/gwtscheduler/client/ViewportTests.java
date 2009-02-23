package gwtscheduler.client;

import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.day.DayView;
import gwtscheduler.client.widgets.view.month.MonthView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.RootPanel;

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

        // months
        MonthView mv = new MonthView();
        ViewportPanel mView = new ViewportPanel();
        mView.add(mv, mv);

        DecoratedTabPanel main = new DecoratedTabPanel();
        main.add(dView, "Days");
        main.add(mView, "Months");
        main.selectTab(0);
        RootPanel.get("calendar-main").add(main);
    }
}
