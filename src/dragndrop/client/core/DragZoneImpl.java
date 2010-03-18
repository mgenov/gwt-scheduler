package dragndrop.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
  private final int CORRECTION = -5;
  private final CursorStyleProvider cursorStyleProvider;
  private final Frame defaultFrame;
  private Display display;

  private DropZone dropZone = null;
  private HashMap<HasMouseDownHandlers, Object> draggingRegister = new HashMap<HasMouseDownHandlers, Object>();
  private HashMap<String, Frame> frameRegister = new HashMap<String, Frame>();
  private ArrayList<HasWidgets> hasDropZones = new ArrayList<HasWidgets>();
  private ArrayList<DropZone> dropZones = new ArrayList<DropZone>();
  private int startX = 0;
  private int startY = 0;
  private int cloneTop = 0;
  private int cloneLeft = 0;
  private int cloneWidth = 0;
  private int cloneHeight = 0;
  private Frame frame;

  public DragZoneImpl(Frame frame, CursorStyleProvider cursorStyleProvider){
    this.defaultFrame = frame;
    this.frame = frame;
    this.cursorStyleProvider = cursorStyleProvider;
  }

  /**
   * Constructor of the DropZone.
   *
   * @param display target display
   */
  public void bindDisplay(Display display) {
    this.display = display;
    attachFrameHandlers(this.frame);
  }

  private void attachFrameHandlers(Frame frame){
    frame.getFrameMouseMoveHandlers().addMouseMoveHandler(new MouseMoveHandler() {
      @Override
      public void onMouseMove(MouseMoveEvent event) {
         mouseMove(event);
      }
    });

    frame.getFrameMouseUpHandlers().addMouseUpHandler(new MouseUpHandler() {
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

        Object o = draggingRegister.get(display.getDragWidget());    // probably not good implementation

        if(o instanceof Draggable){
          Draggable draggable = (Draggable)o;
          cloneWidth = draggable.getWidth();
          cloneHeight = draggable.getHeight();
        } else {
          cloneWidth = display.getSourceWidth();
          cloneHeight = display.getSourceHeight();
        }

        cloneTop = display.getSourceTop();
        cloneLeft = display.getSourceLeft();

        frame = frameRegister.get(o.getClass().getName());
        if(frame == null){
          frame = DragZoneImpl.this.defaultFrame;
        }
        frame.dropObject(o);
        frame.setWidth(cloneWidth + 1);
        frame.setHeight(cloneHeight + 1);

        int[] position = calculatePosition(startX, startY);

        display.addFrame(frame, position[0], position[1]);
//        frame.go(DragZoneImpl.this, );
        frame.captureFrame();
      }
    });
  }

  private int[] calculatePosition(int mouseX, int mouseY){
    int x = (mouseX - (startX - cloneLeft)) + CORRECTION - display.getLeft();
    int y = (mouseY - (startY - cloneTop)) + CORRECTION - display.getTop();
    return new int[] {x, y};
  }

  private void mouseMove(MouseMoveEvent event){

    boolean isDragWidgetStored = display.isDragWidgetStored();

    // user is not dragging anything (Probably not needed. because user click on something and he dragging the frame. so he will always drag something)
    if (!isDragWidgetStored) {
      return;
    }

    int mouseX = event.getClientX();
    int mouseY = event.getClientY();
    int[] position = calculatePosition(mouseX, mouseY);

//    frame.go(DragZoneImpl.this, position[0], position[1]);
    display.addFrame(frame, position[0], position[1]);

    DropZone dropZone = findDropZone(mouseX, mouseY);

    if(dropZone != null && this.dropZone == null){
      // fires event when attachResizeHelper in drop zone.
      this.dropZone = dropZone;
      fireEvent(dropZone, new DragInEvent(frame, mouseX, mouseY));
      // set cursor style when drag in drop zone.
      frame.setCursorStyle(cursorStyleProvider.getPointer());

    } else if(dropZone == null && this.dropZone != null){
      // fires event when attachResizeHelper out from drop zone.
      display.fireEvent(this.dropZone, new DragOutEvent(frame));
      this.dropZone = null;
      // set cursor style when drag out drop zone.
      frame.setCursorStyle(cursorStyleProvider.getNotAllowed());

    } else if(this.dropZone != null){
      // fires event when dragging over drop zone.
      display.fireEvent(this.dropZone, new DragOverEvent(this, mouseX, mouseY));
    }
  }

  private void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event){
    display.fireEvent(dropZone, event);
  }

  private DropZone findDropZone(int x, int y){
    DropZone dropZone = display.findDropZone(dropZones, x, y);
    if(dropZone != null){
      return dropZone;
    }

    return display.getDropZone(hasDropZones, x, y);
  }

  private void mouseUp(MouseUpEvent event){
    frame.releaseFrameCapture();
    display.removeFrame(frame);
//    frame.removeFrameFromDragZone(DragZoneImpl.this);

    if(hasDropZones.size() == 0 && dropZones.size() == 0){
      GWT.log("No registered drop zones!", null);
      return;
    }

    // user has released object when it's position was over a drop zone
    DropZone dropZone = findDropZone(event.getClientX(), event.getClientY());

    // we have to fire drop event to indicate that object has been dropped in the drop zone
    if(dropZone != null){
      int endX = event.getClientX();
      int endY = event.getClientY();

      Object o = draggingRegister.get(display.getDragWidget()); // probably not good implementation
      if(o instanceof Draggable){
        Draggable draggable = (Draggable)o;
        display.dropTo(dropZone, draggable.getSourceWidget(), draggable.getDropObject(), startX, startY, endX, endY);
      } else {
        display.dropTo(dropZone, draggingRegister.get(display.getDragWidget()), startX, startY, endX, endY);
      }
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
   * Add draggable element.
   * @param draggable draggable element.
   */
  @Override
  public void add(Draggable draggable) {
    add(draggable.getHasMouseDownHandler(), draggable);
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
   * Add new root who contains drop zones. This roots will be searched to find drop zones. If you have some Widget in
   * the tail that not implements HasWidgets interface (something like Composite) the search finished at this widget.
   * @param root widget who implements HasWidgets.
   */
  @Override
  public void addDropZoneRoot(HasWidgets root) {
    hasDropZones.add(root);
  }

  /**
   * Register drop zone root. All elements in the list will be iterated and all child's on HasWidget elements will be
   * iterated during search DropZone. If you have some Widget in the tail that not implements HasWidgets interface
   * (something like Composite) the search finished at this widget.
   * @param roots list with root widgets that will be iterated during searching the DropZone.
   */
  @Override
  public void addDropZoneRoot(List<HasWidgets> roots) {
    hasDropZones.addAll(roots);
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

  public void registerFrame(Frame frame, Class... clazz){
    attachFrameHandlers(frame);
    for(Class c : clazz){
      frameRegister.put(c.getName(), frame);
    }
  }

  @Override
  public Frame getCurrentFrame() {
    return frame;
  }

  @Override
  public void setFrameWindowPosition(int left, int top) {
    display.addFrame(frame, left - display.getLeft(), top - display.getTop());
  }

  @Override
  public void makeDragZone(AbsolutePanel panel) {
    display.changeAbsolutePanel(panel);
  }

  @Override
  public void addDropZone(DropZone dropZone) {
    dropZones.add(dropZone);
  }
}
