package gwtscheduler.client.dragndrop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
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

  private Display display;
  private DropZone dropZone = null;

  private HashMap<HasMouseDownHandlers, Object> draggingRegister = new HashMap<HasMouseDownHandlers, Object>();
  private ArrayList<HasWidgets> dropZones = new ArrayList<HasWidgets>();
  private int startX = 0;
  private int startY = 0;
  private int cloneTop = 0;
  private int cloneLeft = 0;
  private int cloneWidth = 0;
  private int cloneHeight = 0;
  private Frame frame;

  public DragZoneImpl(Frame frame){
    this.frame = frame;
  }

  /**
   * Constructor of the DropZone.
   *
   * @param display target display
   */
  public void bindDisplay(Display display) {
    this.display = display;

    this.frame.getFrameMouseMoveHandlers().addMouseMoveHandler(new MouseMoveHandler() {
      @Override
      public void onMouseMove(MouseMoveEvent event) {
         mouseMove(event);
      }
    });

    this.frame.getFrameMouseUpHandlers().addMouseUpHandler(new MouseUpHandler() {
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

        cloneWidth = display.getSourceWidth();
        cloneHeight = display.getSourceHeight();

        cloneTop = display.getSourceTop();
        cloneLeft = display.getSourceLeft();

        frame.setFrameSize(cloneWidth, cloneHeight);

        frame.go(DragZoneImpl.this, startX - (startX + cloneLeft), startY - (startY + cloneTop));
        frame.captureFrame();
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
      // fires event when get in drop zone.
      this.dropZone = dropZone;
      fireEvent(dropZone, new DragInEvent(frame, mouseX, mouseY));

    } else if(dropZone == null && this.dropZone != null){
      // fires event when get out from drop zone.
      display.fireEvent(this.dropZone, new DragOutEvent(frame));
      this.dropZone = null;

    } else if(this.dropZone != null){
      // fires event when dragging over drop zone.
      display.fireEvent(this.dropZone, new DragOverEvent(frame, mouseX, mouseY));
    }

    frame.go(DragZoneImpl.this, mouseX - (mouseX + cloneLeft), mouseX - (mouseX + cloneTop)); // TODO: make correction when dragging
  }

  private void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event){
    display.fireEvent(dropZone, event);
  }

  private void mouseUp(MouseUpEvent event){
    frame.releaseFrameCapture();
    frame.removeFrameFromPanel(DragZoneImpl.this);

    if(dropZones.size() == 0){
      GWT.log("No registered drop zones!", null);
    }

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
   * Add widget to drag zone view with given coordinates.
   * @param widget to be added.
   * @param left coordinate.
   * @param top coordinate.
   */
  @Override
  public void addWidget(Widget widget, int left, int top) {
    display.addWidget(widget, left, top);
  }

  /**
   * Add widget to drag zone.
   * @param widget to be added.
   */
  @Override
  public void addWidget(Widget widget) {
    display.addWidget(widget);
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
   * Set size of drag zone.
   * @param width of the drag zone.
   * @param height of the drag zone.
   */
  @Override
  public void setSize(String width, String height) {
    display.setSize(width, height);
  }

  /**
   * Set style name for dragging frame.
   * @param styleName style name.
   */
  @Override
  public void setFrameStyle(String styleName) {
    frame.setFrameStyle(styleName);    
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

  /**
   * Remove widget from drag zone.
   * @param widget to be removed.
   */
  @Override
  public void removeWidget(Widget widget) {
    display.removeWidget(widget);
  }

}
