package gwtscheduler.client.dragndrop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;

/**
 * This object cares about dragging widgets over drag zone and drop objects over drop zone when drag stops.
 * When instancing this object accept AbsolutePanel where widgets will be dragged. New widget who will be registered for
 * dragging will be wrapped and placed on given coordinates on absolute panel. For every given widget can be registered 
 * object who will be dropped when dragging stops. Object for dropping can be the same widget or another object. When
 * object is dropped over drop zone, event is fired to the given drop zone.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DraggerImpl implements Dragger{
  private AbsolutePanel absolutePanel;
  private Label frame = new Label();
  private DragWrapper dragWidget;
  private HashMap<DragWrapper, Object> draggingRegister = new HashMap<DragWrapper, Object>();
  private Widget dropZone = null;

  /**
   * Constructor of the dragger. Accepts AbsolutePanel where widgets will be dragged.
   *
   * @param absolutePanel dragging zone.
   */
  public DraggerImpl(AbsolutePanel absolutePanel) {
    this.absolutePanel = absolutePanel;

    frame.setStyleName("dragFrame");

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

  /**
   * Register widget for dragging and object for dropping over drop zone.
   *
   * @param widget this will be dragged.
   */
  private void registerDraggable(HasMouseDownHandlers widget) {
    widget.addMouseDownHandler(new MouseDownHandler(){
      public void onMouseDown(MouseDownEvent mouseDownEvent) {
        mouseDown(mouseDownEvent);
      }
    });
  }

  private void mouseDown(MouseDownEvent event){
    dragWidget = (DragWrapper) event.getSource();
    event.preventDefault();

    int x = event.getClientX();
    int y = event.getClientY();

    int cloneWidth = dragWidget.getOffsetWidth();
    int cloneHeight = dragWidget.getOffsetHeight();

    frame.setPixelSize(cloneWidth, cloneHeight);
    absolutePanel.add(frame, x-10, y-10);

    DOM.setCapture(frame.getElement());
//    GWT.log("On Mouse Down", null);
  }

  private void mouseMove(MouseMoveEvent event){
    // TODO: make movement correction!
    if (dragWidget == null) {
      return;
    }

    int mouseX = event.getNativeEvent().getClientX();
    int mouseY = event.getNativeEvent().getClientY();

    Widget dropZone = getWidgetOnPosition(mouseX, mouseY);

    if(dropZone != null && this.dropZone == null){
      this.dropZone = dropZone;
      DragOverEvent dragOver = new DragOverEvent(frame, mouseX, mouseY);
      dragOver.fire(dropZone);
    } else if(dropZone == null && this.dropZone != null){
      DragOutEvent dragOut = new DragOutEvent(frame);
      this.dropZone.fireEvent(dragOut);
      this.dropZone = null;
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

  /**
   * Winds drop zone positioned on given coordinates.
   * @param x left.
   * @param y top.
   * @return return drop zone from coordinates or null when drop zone is not fount on given coordinates.
   */
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

  /**
   * Sending event to drop zone.
   * @param widget drop zone widget.
   * @param x mouse left coordinates when object has been dropped over drop zone.
   * @param y mouse top coordinates when object has been dropped over drop zone.
   */
  private void dropTo(Widget widget, int x, int y){
    DropEvent dropEvent = new DropEvent(dragWidget, draggingRegister.get(dragWidget));
    // here can be implemented logic that add more data in the event.
    // data like coordinates on draggable widget, parent of draggable widget and stuff like that.
    dropEvent.setMouseX(x);
    dropEvent.setMouseY(y);
    dropEvent.fire(widget);
  }

  /**
   * Check if given point with coordinates is in given widget.
   * @param x left mouse coordinate.
   * @param y top mouse coordinate.
   * @param w widget who need to be checked.
   * @return true if given point is over widget area.
   */
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

  /**
   * Add new widget to the drag zone. Given widget will be wrapped and placed on the dragging zone. Widget will be
   * removed from parent.
   * @param widget this widget that will be dragged.
   * @param o object who will be dropped over drop zone.
   * @param left position on drag zone from left .
   * @param top position on drag zone from top.
   */
  @Override
  public void add(Widget widget, Object o, int left, int top) {
    if(widget.getParent() != null){
      widget.removeFromParent();
    }
    DragWrapperImpl dragWrapper = new DragWrapperImpl();
    dragWrapper.add(absolutePanel, widget, left, top);
    draggingRegister.put(dragWrapper, o);
    registerDraggable(dragWrapper);
  }

  /**
   * Set style name for dragging frame.
   * @param styleName style name.
   */
  @Override
  public void setFrameStyle(String styleName) {
    frame.setStyleName(styleName);
  }

}
