package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashMap;

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
  private ArrayList<HasWidgets> dropZones = new ArrayList<HasWidgets>();

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
   */
  private void registerDraggable(HasMouseDownHandlers targetObject) {

    targetObject.addMouseDownHandler(new MouseDownHandler(){
      public void onMouseDown(MouseDownEvent event) {

        display.storeDragWidget(event);
        event.preventDefault();

        int x = event.getClientX();
        int y = event.getClientY();

        int cloneWidth = display.getSourceWidth();
        int cloneHeight = display.getSourceHeight();

        display.setFrameSize(cloneWidth, cloneHeight);

        display.addFrameAtPosition(x-10,y-10);
        display.captureFrame();
      }
    });
  }
  // TODO:
  private void mouseMove(MouseMoveEvent event){

    boolean isDragWidgetStored = display.isDragWidgetStored();

    // TODO: Probably not needed. because user click on something and he dragging the frame. so he will always drag something
    
    // user is not dragging anything
    if (!isDragWidgetStored) {
      return;
    }

    int mouseX = event.getClientX();
    int mouseY = event.getClientY();


    DropZone dropZone = display.getDropZone(dropZones, mouseX, mouseY);

    if(dropZone != null && this.dropZone == null){

      this.dropZone = dropZone;

      display.fireDragOverEvent(dropZone, mouseX, mouseY); // TODO: Rename DragOverEvent to DragInEvent. DragOverEvent need to be fired all time when dragging over drag zone

    } else if(dropZone == null && this.dropZone != null){

      display.fireDragOutEvent(dropZone);
    }

    display.addFrameAtPosition(mouseX - 10, mouseY - 10); // TODO: make correction when dragging
  }

  private void mouseUp(MouseUpEvent event){
    display.releaseFrameCapture();
    display.removeFrameFromPanel();

    // user has released object when it's position was over a drop zone
    DropZone dropZone = display.getDropZone(dropZones, event.getClientX(), event.getClientY());

    // we have to fire drop event to indicate that object has been dropeed in the drop zone
    if(dropZone != null){
      display.dropTo(dropZone, event.getClientX(), event.getClientY(), draggingRegister.get(display.getDragWidget()));
    }
  }

  /**
   * Add new widget to the drag zone. Given widget will be wrapped and placed on the dragging zone. Widget will be
   * removed from parent.
   * @param widget this widget that will be dragged.
   * @param o object who will be dropped over drop zone.
   */
  @Override
  public void add(HasMouseDownHandlers widget, Object o) {
    draggingRegister.put(widget, o);
    registerDraggable(widget);
  }

  @Override
  public void addWidget(Widget widget, int left, int top) {
    display.addWidget(widget, left, top);
  }

  @Override
  public void registerDropZoneRoot(HasWidgets root) {
    dropZones.add(root);
  }

  @Override
  public void setLeft(int left) {
    display.setLeft(left);
  }

  @Override
  public void setTop(int top) {
    display.setTop(top);
  }

  @Override
  public void setSize(int width, int height) {
    display.setSize(width, height);
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
