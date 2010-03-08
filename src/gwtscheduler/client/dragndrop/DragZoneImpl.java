package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.*;
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
class DragZoneImpl implements DragZone {

  private final Display display;
  private DropZone dropZone = null;

  private HashMap<HasMouseDownHandlers, Object> draggingRegister = new HashMap<HasMouseDownHandlers, Object>();
  private ArrayList<HasWidgets> dropZones = new ArrayList<HasWidgets>();
  private int startX = 0;
  private int startY = 0;

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

        startX = event.getClientX();
        startY = event.getClientY();

        int cloneWidth = display.getSourceWidth();
        int cloneHeight = display.getSourceHeight();

        display.setFrameSize(cloneWidth, cloneHeight);

        display.addFrameAtPosition(startX-10, startY-10);
        display.captureFrame();
      }
    });
  }

  private void mouseMove(MouseMoveEvent event){

    boolean isDragWidgetStored = display.isDragWidgetStored();

    // user is not dragging anything (Probably not needed. because user click on something and he dragging the frame. so he will always drag something)
    if (!isDragWidgetStored) {
      return;
    }

    int mouseX = event.getClientX();
    int mouseY = event.getClientY();

    DropZone dropZone = display.getDropZone(dropZones, mouseX, mouseY);

    if(dropZone != null && this.dropZone == null){

      this.dropZone = dropZone;
      display.fireDragInEvent(dropZone, mouseX, mouseY);

    } else if(dropZone == null && this.dropZone != null){

      display.fireDragOutEvent(this.dropZone);
      this.dropZone = null;

    } else if(this.dropZone != null){

      display.fireDragOverEvent(this.dropZone, mouseX, mouseY);

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
      display.dropTo(dropZone, draggingRegister.get(display.getDragWidget()), startX, startY, event.getClientX(), event.getClientY());
    }
  }

  /**
   * Add MouseDownHandler on given widget and register object that need to be dropped over drop zone when drag stops.
   * @param widget to attach mouse down handler.
   * @param o object who will be dropped over drop zone.
   */
  @Override
  public void add(HasMouseDownHandlers widget, Object o) {
    draggingRegister.put(widget, o);
    registerDraggable(widget);
  }

  /**
   * Add widget to be added on drag zone view with given coordinates.
   * @param widget to be added.
   * @param left
   * @param top
   */
  @Override
  public void addWidget(Widget widget, int left, int top) {
    display.addWidget(widget, left, top);
  }

  /**
   * Add new root who contains drop zones. This roots will be searched to find drop zones.
   * @param root widget who implements HasWidgets.
   */
  @Override
  public void registerDropZoneRoot(HasWidgets root) {
    dropZones.add(root);
  }

  /**
   * Set size for a drop zone.
   * @param width in pixels;
   * @param height in pixels.
   */
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

  /**
   * Attach DragZone view to the parent widget.
   * @param parent widget where drag zone will be attached.
   */
  @Override
  public void go(HasWidgets parent) {
    parent.add((Widget)display);
  }

  /**
   * Return an instance of DragZoneView.
   * @return drag zone view who is AbsolutePanel.
   */
  @Override
  public HasWidgets getDragZone() {
    return display.getContainer();
  }

}
