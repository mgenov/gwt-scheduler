package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.interfaces.IEventView;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.event.Event;

import java.util.Collection;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Enter class description here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DayView extends Composite implements IEventView, IViewportResizeHandler {

    /** main container */
    private VerticalPanel container;

    /**
     * Default constructor.
     */
    public DayView() {
        container = new VerticalPanel();
        initWidget(container);

        container.add(new Label("xxx"));
        // this helps visual debug
        DebugUtils.addBorder(getElement());
    }

    public void onViewportResize(ViewportResizeEvent event) {
        container.setSize(event.width + "px", "1000px");
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
