package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

/**
 * This widget will wrap widget that will be dragged.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
class DragWrapperImpl extends Composite implements DragWrapper{

  public void add(AbsolutePanel panel, final Widget widget, int left, int top) {
    initWidget(widget);

    if(widget instanceof DropZone){
      addDropHandler(new DropHandler(){
        @Override
        public void onDrop(DropEvent event) {
          event.fire(widget);
        }
      });

      addDragOverlapHandler(new DragOverlapHandler(){
        @Override
        public void onDragOverlap(DragOverlapEvent event) {
          event.fire(widget);
        }
      });
    }


    panel.add(this, left, top);
  }
  
  public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
    return addDomHandler(handler, MouseDownEvent.getType());
  }

  public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
    return addDomHandler(handler, MouseMoveEvent.getType());
  }

  public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
    return addDomHandler(handler, MouseUpEvent.getType());
  }

  @Override
  public void addDropHandler(DropHandler handler) {
    addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public void addDragOverlapHandler(DragOverlapHandler handler) {
    addHandler(handler, DragOverlapEvent.TYPE);
  }
}
