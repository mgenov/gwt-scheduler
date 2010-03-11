package gwtscheduler.common.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import org.goda.time.Interval;

/**
 * 
 * TODO: Fire event clicks
 * TODO: Update event interval
 * TODO: Resize event when user clicks on it's bottom line and starts dragging it
 * TODO: Move event when user clicks on it's header and starts dragging it
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

  /**
   * HandlerManager provides a notification mechanism for several events that are fired when user interacts with event.
   */
  private final HandlerManager eventHandler = new HandlerManager(null);

  /**
   * The original event that is holding model values and default event behaviour.
   */
  private final Event event;
  /** Target display that is representing event's UI. */
  private Display display;

  /** Holds the event position on the screen. */
  private EventPosition position = new EventPosition();

  /**
   * Default constructor.
   *
   * @param event the event that is wrapping to provided calendar event.
   */
  public CalendarEvent(Event event) {
    this.event = event;
  }

  /**
   * Binds the provided display to the current event.
   *
   * @param display the target display that is representing event's UI.
   */
  public void bindDisplay(Display display) {
    this.display = display;

    display.setHeaderTitle(event.getTitle());
  }

  /**
   * Sets the width and height of the provided event.
   *
   * @param width  the new width to be set
   * @param height the new height to be set
   */
  public void setSize(int width, int height) {
    display.setViewWidth(width);
    display.setViewHeight(height);
  }


  /**
   * Gets event's interval.
   * @return the event interval
   */
  public Interval getInterval() {
    return event.getInterval();
  }

  /**
   * Sets new event interval.
   * @param interval the new interval to be set
   */
  public void setInterval(Interval interval) {
    event.setInterval(interval);
  }

  /**
   * EventPosition class holds information
   * @return
   */
  public EventPosition getPosition() {
    return position;
  }

  /**
   * Sets position of the current event.
   * 
   * @param position
   */
  public void setPosition(EventPosition position) {
    this.position = position;
  }

  /**
   * Attaches current event to the provided absolute panel.
   * <p/>
   * Make note that the coupling with the AbsolutePanel is really bad idea, but currently
   * GWT code doesn't provide an interface that wraps the AbsolutePanel method's such as
   * HasWidgets interface of all widgets that are having child widgets.
   * <p/>
   * To re-size the existing event you have to call <code>setSize</code> method.
   *
   * @param parent the parent panel to which current event will be atached
   * @param left   event's left position
   * @param top    the top event's top position
   */
  public void go(AbsolutePanel parent, int left, int top) {
    parent.add((Widget) display, left, top);
  }


  /**
   * Registers a new {@link com.google.gwt.event.dom.client.ClickHandler} to the current calendar event.
   *
   * @param handler the handler to be registered
   * @return HandlerRegistration class that may be used for un-registering of the registered handler.
   */
  @Override
  public HandlerRegistration addClickHandler(ClickHandler handler) {
    return eventHandler.addHandler(ClickEvent.getType(), handler);
  }

  /**
   * Fires event to the client classes that are having registered handlers for that instance of this event.
   *
   * @param event the event to be fired to the target handlers
   */
  @Override
  public void fireEvent(GwtEvent<?> event) {
    eventHandler.fireEvent(event);
  }
}