package dragndrop.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * DragZoneImpl represents an implementation of DragZone that provides client code
 * with ability to drag objects over this drag zone.
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
  private Object dropObject;
  private int[] dragStartPosition;

  DragZoneImpl(Frame frame, CursorStyleProvider cursorStyleProvider) {
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

  private void attachFrameHandlers(Frame frame) {
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

    targetObject.addMouseDownHandler(new MouseDownHandler() {
      public void onMouseDown(MouseDownEvent event) {

        display.storeDragWidget(event);
        event.preventDefault();

        startX = event.getClientX();
        startY = event.getClientY();
        
        dragStartPosition = new int[]{startX, startY};

        dropObject = draggingRegister.get(display.getDragWidget());

        if (dropObject instanceof Draggable) {
          Draggable draggable = (Draggable) dropObject;
          cloneWidth = draggable.getWidth();
          cloneHeight = draggable.getHeight();
        } else {
          cloneWidth = display.getSourceWidth();
          cloneHeight = display.getSourceHeight();
        }

        cloneTop = display.getSourceTop();
        cloneLeft = display.getSourceLeft();

        if(dropObject != null){
          frame = frameRegister.get(dropObject.getClass().getName());
        }
        
        if (frame == null) {
          frame = DragZoneImpl.this.defaultFrame;
        }
        
        frame.dropObject(dropObject);
        frame.setWidth(cloneWidth + 1);
        frame.setHeight(cloneHeight + 1);

        int[] position = calculatePosition(startX, startY);

        display.addFrame(frame, position[0], position[1]);
        frame.captureFrame();

        display.fireEvent(new DragStartEvent(dragStartPosition, frame, dropObject));
      }
    });
  }

  private int[] calculatePosition(int mouseX, int mouseY) {
    int x = (mouseX - (startX - cloneLeft)) + CORRECTION - display.getLeft();
    int y = (mouseY - (startY - cloneTop)) + CORRECTION - display.getTop();
    return new int[]{x, y};
  }

  private void mouseMove(MouseMoveEvent event) {

    boolean isDragWidgetStored = display.isDragWidgetStored();

    // user is not dragging anything (Probably not needed. because user click on something and he dragging the frame. so he will always drag something)
    if (!isDragWidgetStored) {
      return;
    }

    int mouseX = event.getClientX();
    int mouseY = event.getClientY();
    int[] currentPosition = new int[]{mouseX, mouseY};
    int[] position = calculatePosition(mouseX, mouseY);

    display.addFrame(frame, position[0], position[1]);

    DropZone dropZone = findDropZone(mouseX, mouseY);

    if (dropZone != null && this.dropZone == null) {
      // fires event when attachResizeHelper in drop zone.
      this.dropZone = dropZone;
      fireEvent(dropZone, new DragInEvent(frame, dragStartPosition, currentPosition, dropObject, this));
      // set cursor style when drag in drop zone.
      frame.setCursorStyle(cursorStyleProvider.getOverDropZoneStyle());

    } else if (dropZone == null && this.dropZone != null) {
      // fires event when attachResizeHelper out from drop zone.
      display.fireEvent(this.dropZone, new DragOutEvent(frame, currentPosition, dragStartPosition, dropObject, this));
      this.dropZone = null;
      // set cursor style when drag out drop zone.
      frame.setCursorStyle(cursorStyleProvider.getNotAllowedStyle());

    } else if (this.dropZone != null) {
      Object dropObject;

      if (this.dropObject instanceof Draggable) {
        Draggable draggable = (Draggable) this.dropObject;
        dropObject = draggable.getDropObject();
      } else {
        dropObject = draggingRegister.get(display.getDragWidget());
      }

      // fires event when dragging over drop zone.
      display.fireEvent(this.dropZone, new DragOverEvent(this, currentPosition, dragStartPosition, dropObject, frame));
    }
  }

  private void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event) {
    display.fireEvent(dropZone, event);
  }

  private DropZone findDropZone(int x, int y) {
    DropZone dropZone = display.findDropZone(dropZones, x, y);
    if (dropZone != null) {
      return dropZone;
    }

    return display.getDropZone(hasDropZones, x, y);
  }

  private void mouseUp(MouseUpEvent event) {
    frame.releaseFrameCapture();
    display.removeFrame(frame);

    if (hasDropZones.size() == 0 && dropZones.size() == 0) {
      GWT.log("No registered drop zones!", null);
      return;
    }

    // user has released object when it's position was over a drop zone
    DropZone dropZone = findDropZone(event.getClientX(), event.getClientY());

    // we have to fire drop event to indicate that object has been dropped in the drop zone
    if (dropZone != null) {
      int endX = event.getClientX();
      int endY = event.getClientY();

      if (dropObject instanceof Draggable) {
        Draggable draggable = (Draggable) dropObject;
        display.dropTo(dropZone, draggable.getSourceWidget(), draggable.getDropObject(), startX, startY, endX, endY);
      } else {
        display.dropTo(dropZone, draggingRegister.get(display.getDragWidget()), startX, startY, endX, endY);
      }
      dropObject = null;
    }
  }

  /**
   * Register widget who have HasMouseDownHandlers and object that be dropped when dragging stops over a drop zone. This
   * method register MouseDownHandler to HasMouseDownHandlers widget. Also registers and object who is dropped when drag
   * stops over drop zone and mouse button is up.
   *
   * @param widget who is dragged.
   * @param object who is dropped when drag stops over drop zone.
   */
  @Override
  public void add(HasMouseDownHandlers widget, Object object) {
    draggingRegister.put(widget, object);
    registerDraggable(widget);
  }

  /**
   * Register {@link dragndrop.client.core.Draggable} object.
   *
   * @param draggable object.
   */
  @Override
  public void add(Draggable draggable) {
    add(draggable.getHasMouseDownHandler(), draggable);
  }

  /**
   * Add widget to drag zone view with given coordinates.
   *
   * @param widget to be added.
   * @param left   coordinate.
   * @param top    coordinate.
   */
  @Override
  public void add(Widget widget, int left, int top) {
    display.addWidget(widget, left, top);
  }

  /**
   * Add root who contains drop zones. This roots will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   *
   * @param root widget who implements HasWidgets.
   */
  @Override
  public void addDropZoneRoot(HasWidgets root) {
    hasDropZones.add(root);
  }


  /**
   * Add list with roots that will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   *
   * @param roots list with widgets who implements HasWidgets.
   */
  @Override
  public void addDropZoneRoot(List<HasWidgets> roots) {
    hasDropZones.addAll(roots);
  }

  /**
   * Set size for a drop zone.
   *
   * @param width  in pixels;
   * @param height in pixels.
   */
  @Override
  public void setSize(int width, int height) {
    display.setPixelSize(width, height);
  }

  /**
   * Set size of drag zone.
   *
   * @param width  of the drag zone.
   * @param height of the drag zone.
   */
  @Override
  public void setSize(String width, String height) {
    display.setSize(width, height);
  }

  /**
   * Sets new style on the dragged frame. Default is "dragFrame".
   *
   * @param styleName style name.
   */
  @Override
  public void setFrameStyle(String styleName) {
    frame.setFrameStyle(styleName);
  }

  /**
   * Attach DragZone view to the parent widget.
   *
   * @param parent widget where drag zone will be attached.
   */
  @Override
  public void go(HasWidgets parent) {
    parent.add((Widget) display);
  }

  /**
   * Return an instance of DragZoneView.
   *
   * @return drag zone view who is AbsolutePanel.
   */
  @Override
  public HasWidgets getDragZone() {
    return display.getContainer();
  }

  /**
   * Register a frame for different object types. This frame is placed on the screen when dragging starts. One frame can
   * be used for more then one different object types. When user try to drag object who don't have registered frame for
   * that object type, then default frame is used for dragging the object.
   *
   * @param frame to be placed on the screen when drag start.
   * @param clazz some class types for witch frame will be used.
   */
  public void registerFrame(Frame frame, Class... clazz) {
    attachFrameHandlers(frame);
    for (Class c : clazz) {
      frameRegister.put(c.getName(), frame);
    }
  }

  /**
   * Get frame that is currently placed on the screen.
   *
   * @return frame placed on the screen.
   */
  @Override
  public Frame getCurrentFrame() {
    return frame;
  }

  /**
   * Sets frame position on the window. Given coordinates is not coordinates for placing the frame on the drag zone. Frame
   * is positioned on the given coordinates on the screen.
   *
   * @param left pixels from left side of the screen.
   * @param top pixels from right side of the screen.
   */
  @Override
  public void setFrameWindowPosition(int left, int top) {
    display.addFrame(frame, left - display.getLeft(), top - display.getTop());
  }

  /**
   * Changes current drag zone with another drag zone. When this is performed old drag zone is removed and all attached
   * widgets is removed.
   * <p>
   * WARNING: if you have widget 'A' that have child 'B', and 'B' is defined like DragZone, you can't attach widget 'A'
   * to the drag zone.
   * Don't do THIS!
   * </p>
   * <pre>
   * DragZone dragZone = Zones.getDragZone();
   * AbsolutePanel a = new AbsolutePanel();
   * AbsolutePanel b = new AbsolutePanel();
   *
   * a.add(b);
   *
   * dragZone.makeDragZone(b);
   * dragZone.go(a);
   *
   * </pre>
   *
   * @param panel new drag zone panel.
   */
  @Override
  public void makeDragZone(AbsolutePanel panel) {
    display.changeAbsolutePanel(panel);
  }

  /**
   * Register DropZone. All registered drop zones is searched first when searching the DragZone. If drop zone is not found
   * in registered drop zones, search continue in registered drop zone roots.
   *
   * @param dropZone add drop zone.
   */
  @Override
  public void addDropZone(DropZone dropZone) {
    dropZones.add(dropZone);
  }

  /**
   * Register {@link dragndrop.client.core.DragStartHandler}. This handler handle {@link dragndrop.client.core.DragStartEvent}.
   *
   * @param handler drag over handler.
   * @return handler registration used to remove handler for event.
   */
  @Override
  public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
    return display.addDragStartHandler(handler);
  }

  /**
   * Add widget to drag zone.
   *
   * @param widget to be added.
   */
  
  @Override
  public void add(Widget widget) {
    display.getContainer().add(widget);
  }

  @Override
  public void clear() {
    display.getContainer().clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return display.getContainer().iterator();
  }

  @Override
  public boolean remove(Widget widget) {
    return display.removeWidget(widget);
  }
}
