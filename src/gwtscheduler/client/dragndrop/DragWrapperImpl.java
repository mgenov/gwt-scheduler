package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * This widget will wrap widget that will be dragged.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
class DragWrapperImpl extends Composite implements DragWrapper{
  private Widget widget;

  public void add(AbsolutePanel panel, final Widget widget, int left, int top) {
    initWidget(widget);

    this.widget = widget;

    if(widget instanceof DropZone){
      addDropHandler(new DropHandler(){
        @Override
        public void onDrop(DropEvent event) {
          event.fire(widget);
        }
      });

      addDragOverHandler(new DragOverHandler(){
        @Override
        public void onDragOverlap(DragOverEvent event) {
          event.fire(widget);
        }
      });

      addDragOutHandler(new DragOutHandler(){
        @Override
        public void onDragUnOverlap(DragOutEvent event) {
          event.fire(widget);
        }
      });
    }

    panel.add(this, left, top);
  }

  @Override
  public Widget getWrappedWidget() {
    return widget;
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
  public void addDragOverHandler(DragOverHandler handler) {
    addHandler(handler, DragOverEvent.TYPE);
  }

  @Override
  public void addDragOutHandler(DragOutHandler handler) {
    addHandler(handler, DragOutEvent.TYPE);
  }
}
