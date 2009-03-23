package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.interfaces.events.IResizeHandler;
import gwtscheduler.client.interfaces.events.ResizeEvent;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class CompositeWeekView extends AbstractCompositeDayView implements IResizeHandler {

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

    public void onResize(ResizeEvent event) {
        container.setSize("100%", "1em");
        grid.onResize(event);
    }
}
