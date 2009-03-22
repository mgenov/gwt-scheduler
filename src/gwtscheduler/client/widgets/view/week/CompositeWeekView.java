package gwtscheduler.client.widgets.view.week;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;

/**
 *
 */
public class CompositeWeekView extends AbstractCompositeDayView implements IViewportResizeHandler {

    private VerticalPanel container;
    private HorizontalGridFill grid;

    @Override
    protected AbstractDayView createDayView() {
        return new WeekView();
    }

    @Override
    protected Widget createTopView(int columns) {
        // return new HorizontalGridFill(1, columns);
        container = new VerticalPanel();
        container.setSize("100%", "100%");

        grid = new HorizontalGridFill(1, columns);
        container.add(grid);
        return container;
        // return labels;
        // return super.createTopView(columns);
    }

    public void onViewportResize(ViewportResizeEvent event) {
        container.setSize("100%", "1em");
        grid.onViewportResize(event);
    }
}
