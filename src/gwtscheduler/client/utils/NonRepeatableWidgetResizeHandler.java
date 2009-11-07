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
public abstract class NonRepeatableWidgetResizeHandler implements
    WidgetResizeHandler {

  private WidgetResizeEvent lastResizeEvent;

  @Override
  public final void onResize(WidgetResizeEvent event) {
    if (!event.equals(lastResizeEvent)) {
      onNewResize(event);
      lastResizeEvent = event;
    }
  }

  /**
   * Fired when a resize event is fired. The fired event is guaranteed to be
   * different from the last one.
   * @param evt the event
   */
  protected abstract void onNewResize(WidgetResizeEvent evt);
}
