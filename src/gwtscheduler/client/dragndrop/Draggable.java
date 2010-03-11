package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  HasMouseDownHandlers getHasMouseDownHandler();

  Object getDropObject();

  int getWidth();

  int getHeight();
}
