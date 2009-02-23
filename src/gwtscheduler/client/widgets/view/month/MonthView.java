package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.IEventView;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.event.Event;

import java.util.Collection;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * View class for months.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthView extends Composite implements IEventView, IViewportResizeHandler {

    /** main container */
    private FlowPanel container;

    public MonthView() {
        container = new FlowPanel();
        initWidget(container);
        DebugUtils.addBorder(getElement());
    }

    public void onViewportResize(ViewportResizeEvent event) {
        setSize(event.width + "px", event.height + "px");
    }

    public void addEvents(Collection<Event> events) {
    }

    public void clearEvents() {
    }

    public void navigateNext() {
    }

    public void navigatePrevious() {
    }

    public void navigateTo(IDate date) {
    }

    public boolean removeEvent(Event event) {
        return false;
    }

}
