package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface represents Object that care about dragging widgets and dropping objects over drop zones.
 * <p>
 * Getting an instance. Getting an instance of drop zone can be made by {@link dragndrop.client.core.Zones}.
 * Read {@link dragndrop.client.core.Zones} for more details about getting instance of DropZone. 
 * </p>
 * <pre>
 * DragZone dropZone = Zones.getDragZone();
 * </pre>
 * <p>
 * There is two approaches for registering draggable widgets. First approach register any object who implements
 * {@link com.google.gwt.event.dom.client.HasMouseDownHandlers}.
 * </p>
 * <pre>
 * HasMouseDownHandlers draggable = new Label(); // Label is only for example. You can add any widget who implements HasMouseDownHandlers.
 * Object dropObject = draggable;                // dropped object can be any object you want to be dropped.
 * dragZone.add(draggable, object);
 * </pre>
 * <p>
 * Second approach register any object who implements {@link dragndrop.client.core.Draggable} interface. Method for
 * registering Draggable objects can accept unlimited number of draggable objects.
 * </p>
 * <pre>
 * Draggable draggable = new MyDraggableObject();
 * Draggable secondDraggable = new MySecondDraggableObject();
 * ....
 * Draggable nDraggable = new MyNDraggableObject();
 * dragZone.add(draggable, secondDraggable, ...., nDraggable);
 * </pre>
 * <p>
 * Widgets can be added directly to the drag zone without setting position on the drag zone panel or added on specific
 * position on the panel.
 * </p>
 * <pre>
 * dragZone.add(new Label());         // attach widget directly on the panel with default coordinates.
 * dragZone.add(new Label(), 10, 20); // attach widget on drop zone on position 10 left and 20 top.
 * </pre>
 * <p>
 * Adding drop zone containers. For more information how to use drop zone containers read in {@link dragndrop.client.core.Zones} documentation.
 * There is two approaches for adding DropZone containers. 
 * </p>
 * <pre>
 * HasWidgets containerOne = new VerticalPanel(); // can be any widget who implements HasWidgets
 * HasWidgets containerTwo = new HorizontalPanel();
 * ......
 * HasWidgets containerN = new AbsolutePanel();
 * dropZone.addDropZoneContainer(containerOne, containerTwo, ...., containerN);
 *
 * or
 *
 * List<HasWidgets> containers = new ArrayList<HasWidgets>();
 * roots.add(containerOne);
 * roots.add(containerTwo);
 * ......
 * roots.add(containerN);
 *
 * dropZone.addDropZoneContainer(containers);
 * </pre>
 * <p>
 * Attaching dragZone to some panel. If dragZone don't have any attached widgets, or size is not set on drop zone, the drop zone will not be visible.
 * </p>
 * <pre>
 * dragZone.go(RootPanel.get("DragZone"));
 *
 * or
 *
 * HasWidgets panel = new VerticalPanel(); // any panel that is instance of HasWidgets.
 * dragZone.go(panel);
 * </pre>
 * <p>
 * DragZone allow to be registered different frames for different dragged objects. Read more information on registerFrame(Frame frame, Class... clazz); method.
 * </p>
 * <pre>
 * Frame frame = new MyFrame(); // MyFrame implements {@link dragndrop.client.core.Frame} interface. Frame will be used for next dragged objects.
 * Object o = new Object();
 * Object b = new Object();
 * ....
 * Object n = new Object();
 *
 * dragZone.registerFrame(frame, o, b, ....., n);
 * </pre>
 *
 * Every given widget is wrapped by another widget who is dragged over drag area. If widget is attached to another
 * parent then parent is removed, widget is wrapped and is set on dragging area Given drop object is only dropped over
 * drop zone. Drop zone must ley on same panel as drag widget. When object is dropped over drop zone, event is fired to
 * given drop zone. Because self widget is not dragged but frame is dragged you can set frame style.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DragZone extends HasWidgets{

  interface Display {

    int getSourceWidth();

    int getSourceHeight();

    void storeDragWidget(MouseDownEvent event);

    boolean isDragWidgetStored();

    DropZone getDropZone(ArrayList<HasWidgets> roots, int x, int y);

    DropZone findDropZone(ArrayList<DropZone> dropZones, int x, int y);

    void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event);
    
    void fireEvent(GwtEvent<?> event);

    void dropTo(DropZone dropZone, Object targetObject, int startX, int startY, int endX, int endY);

    void dropTo(DropZone dropZone, Widget sourceWidget, Object dropObject, int startX, int startY, int endX, int endY);

    HasWidgets getContainer();

    void setPixelSize(int width, int height);

    void addWidget(Widget widget, int left, int top);

    HasMouseDownHandlers getDragWidget();

    void setSize(String width, String height);

    boolean removeWidget(Widget widget);

    int getSourceTop();

    int getSourceLeft();

    int getLeft();

    int getTop();

    void addFrame(Frame frame, int left, int top);

    void removeFrame(Frame frame);

    void changeAbsolutePanel(AbsolutePanel panel);

    HandlerRegistration addDragStartHandler(DragStartHandler handler);
  }

  /**
   * Register widget who have HasMouseDownHandlers and object that be dropped when dragging stops over a drop zone. This
   * method register MouseDownHandler to HasMouseDownHandlers widget. Also registers and object who is dropped when drag
   * stops over drop zone and mouse button is up.
   * 
   * @param widget who is dragged.
   * @param object who is dropped when drag stops over drop zone.
   */
  void add(HasMouseDownHandlers widget, Object object);

  /**
   * Register {@link dragndrop.client.core.Draggable} object.
   *
   * @param draggable object.
   */
  void add(Draggable... draggable);

  /**
   * Add widget to drag zone with given coordinates.
   * 
   * @param widget to be added.
   * @param left coordinate.
   * @param top coordinate.
   */
  void add(Widget widget, int left, int top);

  /**
   * Sets new style on the dragged frame. Default is "dragFrame".
   * 
   * @param styleName style name.
   */
  void setFrameStyle(String styleName);

   /**
   * Add container who contains drop zones. This container will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   * 
   * @param container widget who implements HasWidgets.
   */
  void addDropZoneContainer(HasWidgets... container);

  /**
   * Add list with containers that will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   *
   * @param containers list with widgets who implements HasWidgets.
   */
  void addDropZoneContainer(List<HasWidgets> containers);

   /**
   * Set size for a drop zone.
    * 
   * @param width in pixels;
   * @param height in pixels.
   */
  void setSize(int width, int height);

  /**
   * Set size for a drop zone.
   * 
   * @param width of the drag zone.
   * @param height of the drag zone.
   */
  void setSize(String width, String height);

  /**
   * Attach DragZone view to the parent widget.
   * 
   * @param parent widget where drag zone will be attached.
   */
  void go(HasWidgets parent);

  /**
   * Return an instance of DragZoneView.
   * 
   * @return drag zone view is AbsolutePanel.
   */
  HasWidgets getDragZone();

  /**
   * Register a frame for different object types. This frame is placed on the screen when dragging starts. One frame can
   * be used for more then one different object types. When user try to drag object who don't have registered frame for
   * that object type, then default frame is used for dragging the object.
   *
   * @param frame to be placed on the screen when drag start.
   * @param clazz some class types for witch frame will be used.
   */
  void registerFrame(Frame frame, Class... clazz);

  /**
   * Get frame that is currently placed on the screen.
   *
   * @return frame placed on the screen.
   */
  Frame getCurrentFrame();

  /**
   * Sets frame position on the window. Given coordinates is not coordinates for placing the frame on the drag zone. Frame
   * is positioned on the given coordinates on the screen.
   *
   * @param left pixels from left side of the screen.
   * @param top pixels from right side of the screen.
   */
  void setFrameWindowPosition(int left, int top);

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
  void makeDragZone(AbsolutePanel panel);

  /**
   * Register DropZone. All registered drop zones is searched first when searching the DragZone. If drop zone is not found
   * in registered drop zones, search continue in registered drop zone roots.
   * 
   * @param dropZone add drop zone.
   */
  void addDropZone(DropZone... dropZone);

  /**
   * Register {@link dragndrop.client.core.DragStartHandler}. This handler handle {@link dragndrop.client.core.DragStartEvent}.
   *
   * @param handler drag over handler.
   * @return handler registration used to remove handler for event.
   */
  HandlerRegistration addDragStartHandler(DragStartHandler handler);
}
