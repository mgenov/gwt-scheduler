package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.resize.DefaultViewportResizeHandler;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrappedWidget;
import gwtscheduler.client.widgets.view.month.composite.MonthRow;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * View class for months.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthView extends WrappedWidget implements IViewportResizeHandler {

    /** Main container */
    private FlowPanel container;
    /** resize handler */
    private DefaultViewportResizeHandler handler;
    /** collection of month rows */
    private List<MonthRow> monthRows;

    /**
     * 
     */
    public MonthView() {
        container = new FlowPanel();
        handler = new DefaultViewportResizeHandler(this);
        wrapWidget(container);

        monthRows = new ArrayList<MonthRow>();

        for (int i = 0; i < 6; i++) {
            MonthRow row = new MonthRow(7);
            monthRows.add(row);
            container.add(row);
        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        float height = ((float) 100 / monthRows.size());
        for (int i = 0; i < monthRows.size(); i++) {

            float top = ((float) 100 / monthRows.size()) * i;
            Element rowElement = monthRows.get(i).getElement();
            DOM.setStyleAttribute(rowElement, "top", top + "%");
            DOM.setStyleAttribute(rowElement, "height", height + "%");
        }
    }

    public void onViewportResize(ViewportResizeEvent event) {
        // we delegate to default handler
        handler.onViewportResize(event);
        for (MonthRow row : monthRows) {
            row.onViewportResize(event);
        }
    }
}
