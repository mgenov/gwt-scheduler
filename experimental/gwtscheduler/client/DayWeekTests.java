package gwtscheduler.client;

import gwtscheduler.client.resources.css.Resources;
import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.day.DayView;
import gwtscheduler.client.widgets.view.week.WeekView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.libideas.client.StyleInjector;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DayWeekTests implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        StyleInjector.injectStylesheet(Resources.CommonCss().getText());
        
        DayView dv = new DayView();
        ViewportPanel dView = new ViewportPanel();
        dView.add(dv, dv);

        // week
        WeekView wv = new WeekView();
        ViewportPanel wView = new ViewportPanel();
        wView.add(wv, wv);


        TabPanel main = new DecoratedTabPanel();
        main.add(dView, "Day");
        main.add(wView, "Week");
        main.selectTab(1);

        RootPanel.get("calendar-main").add(main);
    }
}
