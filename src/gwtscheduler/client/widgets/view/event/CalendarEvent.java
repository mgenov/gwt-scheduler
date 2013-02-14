package gwtscheduler.client.widgets.view.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.Draggable;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.common.util.Interval;

/**
 *  Represents Event that is added in the scheduler and is involved in different manipulations
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEvent implements Draggable {

  public interface Display {

    HasMouseDownHandlers getHeader();

    void setHeaderTitle(String title);

    void setViewWidth(int width);

    void setViewHeight(int height);

    int getWidth();

    int getHeight();

    HasMouseDownHandlers getFooter();

    HasClickHandlers getCloseBtn();

    HasClickHandlers getBody();

    void setDescription(String description);

    void setHeaderColor(String headerColor);

    void setBodyColor(String bodyColor);

    void setTitleColor(String titleColor);

    void setTextColor(String textColor);
  }

  /**
   * The original event that is holding model values and default event behaviour.
   */
  private final Event event;
  /** Target display that is representing event's UI. */
  private Display display;

  /** Holds the event position on the screen. */
  private EventPosition position = new EventPosition();
  private int[] startCellPosition;
  private int[] endCellPosition;
  private EventBus eventBus;
  private Interval interval;

  /**
   * Default constructor.
   *
   * @param event the event that is wrapping to provided calendar event.
   * @param position
   * @param endCellPosition
   * @param eventBus
   */
  public CalendarEvent(Event event, EventPosition position, int[] startCellPosition, int[] endCellPosition, EventBus eventBus) {
    this.event = event;
    interval = new Interval(event.getDurationInterval().getStart().getTime(),event.getDurationInterval().getEnd().getTime());
    this.position = position;
    this.startCellPosition = startCellPosition;
    this.endCellPosition = endCellPosition;
    this.eventBus = eventBus;
  }

  /**
   * Binds the provided display to the current event.
   *
   * @param display the target display that is representing event's UI.
   */
  public void bindDisplay(Display display) {
    this.display = display;
    if(event instanceof HasColors){
      setEventColors((HasColors) event);
    }

    display.setHeaderTitle(event.getTitle());
    display.setDescription(event.getDescription());

    display.getCloseBtn().addClickHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent e) {
        eventBus.fireEvent(new CalendarEventDeleteEvent(event));
      }
    });
    
    display.getBody().addClickHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent clickEvent) {
        eventBus.fireEvent(new EventClickEvent(event, interval));
      }
    });
  }

  /**
   * Sets the event window colors.
   * @param colors object that contains colors.
   */
  public void setEventColors(HasColors colors){
    display.setHeaderColor(colors.getHeaderColor());
    display.setBodyColor(colors.getBodyColor());
    display.setTitleColor(colors.getTitleColor());
    display.setTextColor(colors.getTextColor());
  }

  /**
   * Sets the width and height of the provided event.
   *
   * @param width  the new width to be set
   * @param height the new height to be set
   */
  public void setSize(int width, int height) {
    display.setViewWidth(width);
    if(height <= 0){
      GWT.log("Event still stay!", null);
    }
    display.setViewHeight(height);
  }


  /**
   * Gets event's interval.
   * @return the event interval
   */
  public Interval getInterval() {
    return interval;
  }

  /**
   * Sets new event interval.
   * @param interval the new interval to be set
   */
  public void setInterval(Interval interval) {
    this.interval = interval;
    event.setDurationInterval(new Interval(interval.getStartMillis(), interval.getEndMillis()));
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
   *
   * <p/>
   * Make note that the coupling with the AbsolutePanel is really bad idea, but currently
   * GWT code doesn't provide an interface that wraps the AbsolutePanel method's such as
   * HasWidgets interface of all widgets that are having child widgets.
   * <p/>
   * To re-size the existing event you have to call <code>setPixelSize</code> method.
   *
   * <p/>The event is attached to the absolute panel by using it's current {@link gwtscheduler.client.widgets.view.event.EventPosition} attribute.
   * 
   * @param parent the parent panel to which current event will be attached
   */
  public void go(AbsolutePanel parent) {
    parent.add((Widget) display, position.getLeft(),position.getTop());
  }

  /**
   * Set height 
   * @param height
   */
  public void setHeight(int height){
    display.setViewHeight(height);
  }

  public void setWidth(int width){
    display.setViewWidth(width);
  }

  public String getEventTitle(){
    return event.getTitle();
  }

  @Override
  public HasMouseDownHandlers getHasMouseDownHandler() {
    return display.getHeader();
  }

  @Override
  public Object getDropObject() {
    return this;
  }

  @Override
  public int getWidth() {
    return display.getWidth();
  }

  @Override
  public int getHeight() {
    return display.getHeight();
  }

  @Override
  public Widget getSourceWidget() {
    return (Widget)display;
  }

  public HasMouseDownHandlers getMouseDownHandlers() {
    return display.getFooter();
  }

  public void removeFromParent(AbsolutePanel parent) {
    parent.remove((Widget) display);
  }

  public Event getEvent() {
    return event;
  }

  public int[] getStartCellPosition() {
    return startCellPosition;
  }

  public int[] getEndCellPosition() {
    return endCellPosition;
  }

  public boolean isEditable(){
    return event.isEditable();
  }
}
