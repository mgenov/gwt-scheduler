package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DragWrapper extends HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseUpHandlers, DropZone{

  void add(AbsolutePanel panel, final Widget widget, int left, int top);

  Widget getWrappedWidget();

  int getOffsetWidth();

  int getOffsetHeight();
}
