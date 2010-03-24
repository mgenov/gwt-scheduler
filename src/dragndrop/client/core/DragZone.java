package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface represents Object that care about dragging widgets and dropping object over drop zones.
 *
 * One simple example how this need to be used:
 *
 * <pre>
 * DraggableWidget draggableWidget = new DraggableWidget(); 
 * DraggablePresenter draggablePresenter = new DraggablePresenter(draggableWidget);
 *
 * DropZone dropZone = new PresenterImplementsDropZone(new DisplayWidget());
 *
 * VerticalPanel dropZonesPanel = new VerticalPanel();
 * verticalPanel.add(dropZone);
 *
 * DragZone dragZone = Zones.getDragZone();
 * dragZone.add((HasMouseDownHandlers)draggableWidget, draggablePresenter);
 *
 * dragZone.addDropZoneRoot(dropZonesPanel);
 *
 * dragZone.go(RootPanel.attachResizeHelper());
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
  void add(Draggable draggable);

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
   * Add root who contains drop zones. This roots will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   * 
   * @param root widget who implements HasWidgets.
   */
  void addDropZoneRoot(HasWidgets root);

  /**
   * Add list with roots that will be searched for drop zones. Read documentation in {@link dragndrop.client.core.Zones}
   * fore more information.
   *
   * @param roots list with widgets who implements HasWidgets.
   */
  void addDropZoneRoot(List<HasWidgets> roots);

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
  void addDropZone(DropZone dropZone);
}
