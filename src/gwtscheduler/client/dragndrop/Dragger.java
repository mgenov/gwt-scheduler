package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;

/**
 * This Dragger register widgets that can be dragged and objects that need to be dropped when dragging stops.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Dragger {

  /**
   * Registering draggable widgets and objects to be dropped when dragging stops.
   *
   * @param widget widget that implements HasMouseDownHandlers and that will be dragged on the screen.
   * @param object that will be dropped when dragging stops.
   */
  void registerDraggable(HasMouseDownHandlers widget, Object object);

}
