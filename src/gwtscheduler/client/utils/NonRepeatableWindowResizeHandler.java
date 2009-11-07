/*
 * Copyright (c) 2009, Estradas de Portugal. All rights reserved. This software
 * is proprietary and belongs to Estradas de Portugal.
 */

/**
 * 
 */
package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;

/**
 * @author malp
 */
public abstract class NonRepeatableWindowResizeHandler implements
    WidgetResizeHandler {

  private WidgetResizeEvent lastResizeEvent;

  @Override
  public final void onResize(WidgetResizeEvent event) {
    if (!eventsAreEqual(event, lastResizeEvent)) {
      onNewResize(event);
      lastResizeEvent = event;
    }
  }

  /**
   * @param evt
   */
  protected abstract void onNewResize(WidgetResizeEvent evt);

  /**
   * Utility to compare two events.
   * @param event the event to compare. Cannot be <code>null</code>
   * @param other the other event
   * @return <code>true</code> if the event's data is the same
   */
  private boolean eventsAreEqual(WidgetResizeEvent event,
      WidgetResizeEvent other) {
    assert event != null : "Event cannot be null";
    if (other != null) {
      return event.width == other.width && event.height == other.height;
    }
    return false;
  }
}
