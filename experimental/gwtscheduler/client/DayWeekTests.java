package gwtscheduler.client;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.view.day.CompositeDayView;
import gwtscheduler.client.widgets.view.week.CompositeWeekView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.libideas.client.StyleInjector;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
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
        StyleInjector.injectStylesheet(Resources.dayWeekCss().getText());

        // DayView dvx = new DayView();
        // ViewportPanel dViewx = new ViewportPanel();
        // dViewx.add(dvx, dvx);
        HTMLPanel fp = new HTMLPanel("<span>blah blah blah <p> other blah  </span>");
        //
        // VerticalPanel vp = new VerticalPanel();
        // vp.add(fp);
        // vp.add(dViewx);

        // DayView dv = new DayView();
        // ViewportPanel dView = new ViewportPanel();
        // dView.add(dv, dv);

        // WeekView wv = new WeekView();
        // ViewportPanel mView = new ViewportPanel();
        // mView.add(wv, wv);

        TabPanel main = new DecoratedTabPanel();

        main.add(new CompositeDayView(), "Day");
        main.add(new CompositeWeekView(), "Week");
        main.add(fp, "FP");

        // main.add(dView, "Day");
        // main.add(mView, "Week");

        // main.add(vp, "Composite");
        // main.add(fp, "Composite");
        main.selectTab(0);

        RootPanel.get("calendar-main").add(main);
    }
}
