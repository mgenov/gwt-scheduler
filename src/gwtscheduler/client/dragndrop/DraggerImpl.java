package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DraggerImpl implements Dragger{
  private AbsolutePanel absolutePanel;
  private DraggedFrame frame = new DraggedFrame();
  private Widget dragWidget;
  private HashMap<HasMouseDownHandlers, Object> draggingRegister = new HashMap<HasMouseDownHandlers, Object>();

  public DraggerImpl(AbsolutePanel absolutePanel) {
    this.absolutePanel = absolutePanel;

    frame.addMouseMoveHandler(new MouseMoveHandler(){
      public void onMouseMove(MouseMoveEvent mouseMoveEvent) {
        mouseMove(mouseMoveEvent);
      }
    });

    frame.addMouseUpHandler(new MouseUpHandler(){
      public void onMouseUp(MouseUpEvent mouseUpEvent) {
        mouseUp(mouseUpEvent);
      }
    });
  }


  public void registerDraggable(HasMouseDownHandlers widget, Object object) {
    draggingRegister.put(widget, object);

    widget.addMouseDownHandler(new MouseDownHandler(){
      public void onMouseDown(MouseDownEvent mouseDownEvent) {
        mouseDown(mouseDownEvent);
      }
    });
  }

  private void mouseDown(MouseDownEvent event){
    dragWidget = (Widget) event.getSource();
    event.preventDefault();

    int x = event.getClientX();
    int y = event.getClientY();

    int cloneWidth = dragWidget.getOffsetWidth();
    int cloneHeight = dragWidget.getOffsetHeight();

    frame.setPixelSize(cloneWidth, cloneHeight);
    absolutePanel.add(frame, x-10, y-10);

    DOM.setCapture(frame.getElement());
  }

  private void mouseMove(MouseMoveEvent event){
    // TODO: make movement correction!
    if (dragWidget == null) {
      return;
    }

    int mouseX = event.getNativeEvent().getClientX();
    int mouseY = event.getNativeEvent().getClientY();

    Widget dropZone = getWidgetOnPosition(mouseX, mouseY);

    if(dropZone != null){
      DragOverlapEvent dragOverlap = new DragOverlapEvent(frame, mouseX, mouseY);
      dragOverlap.fire(dropZone);
    }

    absolutePanel.add(frame, mouseX-10, mouseY-10);
  }

  private void mouseUp(MouseUpEvent event){
    DOM.releaseCapture(frame.getElement());
    absolutePanel.remove(frame);

    Widget dropZone = getWidgetOnPosition(event.getClientX(), event.getClientY());
    if(dropZone != null){
      dropTo(dropZone, event.getClientX(), event. getClientY());    
    }
  }

  private Widget getWidgetOnPosition(int x, int y){
    for(int i = 0; i < absolutePanel.getWidgetCount(); i++){
      Widget dropWidget = absolutePanel.getWidget(i);
      if (dropWidget instanceof DropZone && dropWidget != dragWidget) {
        if (checkPosition(x, y, dropWidget)) {
          return dropWidget;
        }
      }
    }
    
    return null;
  }

  private void dropTo(Widget widget, int x, int y){
    DropEvent dropEvent = new DropEvent(dragWidget, draggingRegister.get(dragWidget));
    // here can be implemented logic that add more data in the event.
    // data like coordinates on draggable widget, parent of draggable widget and stuff like that.
    dropEvent.setMouseX(x);
    dropEvent.setMouseY(y);
    dropEvent.fire(widget);
  }

  private boolean checkPosition(int x, int y, Widget w) {
    // TODO: make better logic for checking if point
    int widgetX = w.getAbsoluteLeft();
    int widgetY = w.getAbsoluteTop();
    int width = w.getElement().getClientWidth();
    int height = w.getElement().getClientHeight();

    if ((x >= widgetX && x <= widgetX + width) && (y >= widgetY && y <= widgetY + height)) {
      return true;
    }
    return false;
  }
}
