package gwtscheduler.common.event;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

/**
 * TODO: Fire event clicks
 * TODO: Update event interval
 * 
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEvent implements HasClickHandlers {

    interface Display {
        // event view that is displaying event as rounded panel

      void setViewWidth(int width);
      void setViewHeight(int height);


      void setHeaderTitle(String title);

    }

    /** The original event that is holding model values and default event behaviour. */
    private final Event event;
    private Display display;

    public CalendarEvent(Event event) {
      this.event = event;
    }

    public void bindDisplay(Display display) {
      this.display = display;

      display.setHeaderTitle(event.getTitle());
    }

    /**
     * Sets the width and height of the provided event.
     *
     * @param width the new width to be set
     * @param height the new height to be set
     */
    public void setSize(int width, int height) {
      display.setViewWidth(width);
      display.setViewHeight(height);
    }

    public Interval getInterval() {
      return event.getInterval();
    }

    public void setInterval(Interval interval)  {
      event.setInterval(interval);

      // update interval
      //display.setIntervalValue(interval.toString());
    }


    /**
     * Attaches current event to the provided absolute panel.
     *
     * Make note that the coupling with the AbsolutePanel is really bad idea, but currently
     * GWT code doesn't provide an interface that wraps the AbsolutePanel method's such as
     * HasWidgets interface of all widgets that are having child widgets.
     * <p/>
     * To re-size the existing event you have to call <code>setSize</code> method.
     *
     * @param parent the parent panel to which current event will be atached
     * @param left event's left position
     * @param top the top event's top position
     */
    public void go(AbsolutePanel parent, int left, int top) {
      parent.add((Widget)display, left, top);
    }


    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
      return null;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {

    }
}
