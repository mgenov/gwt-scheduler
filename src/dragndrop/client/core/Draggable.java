package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  HasMouseDownHandlers getHasMouseDownHandler();

  Object getDropObject();

  int getWidth();

  int getHeight();

  Widget getSourceWidget();
}
