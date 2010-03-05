package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.Iterator;

/**
 * DragZoneImpl represents an implementation of DragZone that provides client code
 * with ability to drag objects over this drag zone.
 *
 * 
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class DragZoneImpl implements DragZone {

  private final Display display;
  private DropZone dropZone = null;

  private HashMap<HasMouseDownHandlers, Object> draggingRegister = new HashMap<HasMouseDownHandlers, Object>();

  /**
   * Constructor of the DropZone.
   *
   * @param display target display
   */
  public DragZoneImpl(Display display) {
    this.display = display;

    display.getFrameMouseMoveHandlers().addMouseMoveHandler(new MouseMoveHandler() {
      @Override
      public void onMouseMove(MouseMoveEvent event) {
         mouseMove(event);
      }
    });

    display.getFrameMouseUpHandlers().addMouseUpHandler(new MouseUpHandler() {
      @Override
      public void onMouseUp(MouseUpEvent event) {
        mouseUp(event);
      }
    });        
  }

  /**
   * Register targetObject for dragging and object for dropping over drop zone. Dragging targetObject must implements HasMouseDownHandlers.
   *
   * @param targetObject this will be dragged.
   * @param object this will be dropped.
   */
  private void registerDraggable(HasMouseDownHandlers targetObject, Object object) {
    draggingRegister.put(targetObject, object);

    targetObject.addMouseDownHandler(new MouseDownHandler(){
      public void onMouseDown(MouseDownEvent event) {

        display.storeDragWidget(event);
        event.preventDefault();

        int x = event.getClientX();
        int y = event.getClientY();

        int cloneWidth = display.getOffsetWidth(event);
        int cloneHeight = display.getOffsetHeight(event);

        display.setFrameSize(cloneWidth, cloneHeight);

        display.addFrameAtPosition(x-10,y-10);
        display.captureFrame();
      }
    });
  }

  private void mouseMove(MouseMoveEvent event){

    boolean isDragWidgetStored = display.isDragWidgetStored();

    // user is not dragging anything
    if (!isDragWidgetStored) {
      return;
    }

    int mouseX = event.getClientX();
    int mouseY = event.getClientY();


    DropZone dropZone = display.getDropZone(mouseX, mouseY);


    if(isDragWidgetStored && this.dropZone == null){
      this.dropZone = dropZone;

      display.fireDragOverEvent(mouseX, mouseY);
      
    } else if(dropZone == null && this.dropZone != null){

      display.fireDragOutEvent();
    }

    display.addFrameAtPosition(mouseX - 10, mouseY - 10);
  }


  private void mouseUp(MouseUpEvent event){

    display.releaseFrameCapture();

    display.removeFrameFromPanel();

    // user has released object when it's position was over a drop zone
    DropZone dropZone = display.getDropZone(event.getClientX(), event.getClientY());


    // we have to fire drop event to indicate that object has been dropeed in the drop zone    
    if(dropZone != null){
        //TODO: Fix me
//      display.dropTo(event.getClientX(), event.getClientY(), draggingRegister.get(dragWidget));
    }
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

    display.attachWidgetToDragWrapper(widget, left, top);

    DragWrapperImpl nextDragDisplay = new DragWrapperImpl(widget);
    nextDragDisplay.go(display.getContainer());
    display.setPosition(nextDragDisplay, left, top);
    

    registerDraggable(nextDragDisplay, o);
        
  }

  @Override
  public void setLeft(int left) {
    display.setLeft(left);
  }

  @Override
  public void setTop(int top) {
    display.setTop(top);
  }

  /**
   * Set style name for dragging frame.
   * @param styleName style name.
   */
  @Override
  public void setFrameStyle(String styleName) {
    display.setFrameStyle(styleName);    
  }

  @Override
  public void go(HasWidgets parent) {
    parent.add((Widget)display);
  }

}
