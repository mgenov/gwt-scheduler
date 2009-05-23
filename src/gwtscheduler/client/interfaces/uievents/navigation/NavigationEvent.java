package gwtscheduler.client.interfaces.uievents.navigation;

import gwtscheduler.common.calendar.IDate;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Navigation events.
 * @author malp
 */
public class NavigationEvent extends GwtEvent<INavigationHandler> {

  /** the event's date */
  public final IDate date;

  /** the operation */
  private final Operation operation;

  /**
   * Defines different operation types.
   * @author malp
   */
  public static enum Operation {
    Back, Forward, Current;
  }

  /**
   * Only available constructor.
   * @param date the event's date
   * @param operation the operation
   */
  public NavigationEvent(IDate date, Operation operation) {
    this.operation = operation;
    this.date = date;
  }

  /**
   * Event type for blur events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<INavigationHandler> TYPE = new Type<INavigationHandler>();

  /**
   * Gets the event type associated with navigation events.
   * @return the handler type
   */
  public static Type<INavigationHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<INavigationHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(INavigationHandler handler) {
    switch (operation) {
      case Back:
        handler.onNavigateBack();
        break;
      case Forward:
        handler.onNavigateForward();
        break;
      case Current:
        handler.onNavigateCurrent(date);
        break;
      default:
        break;
    }
  }

}
