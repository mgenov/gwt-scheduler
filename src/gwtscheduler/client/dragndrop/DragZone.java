package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
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
 * dragZone.go(RootPanel.get());
 * </pre>
 *
 * Every given widget is wrapped by another widget who is dragged over drag area. If widget is attached to another
 * parent then parent is removed, widget is wrapped and is set on dragging area Given drop object is only dropped over
 * drop zone. Drop zone must ley on same panel as drag widget. When object is dropped over drop zone, event is fired to
 * given drop zone. Because self widget is not dragged but frame is dragged you can set frame style.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DragZone {

  interface Display {

    int getSourceWidth();

    int getSourceHeight();

    void storeDragWidget(MouseDownEvent event);

    boolean isDragWidgetStored();

    DropZone getDropZone(ArrayList<HasWidgets> roots, int x, int y);

    void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event);

    void dropTo(DropZone dropZone, Object targetObject, int startX, int startY, int endX, int endY);

    void dropTo(DropZone dropZone, Widget sourceWidget, Object dropObject, int startX, int startY, int endX, int endY);

    HasWidgets getContainer();

    void setSize(int width, int height);

    void addWidget(Widget widget, int left, int top);

    HasMouseDownHandlers getDragWidget();

    void setSize(String width, String height);

    void addWidget(Widget widget);

    void removeWidget(Widget widget);

    int getSourceTop();

    int getSourceLeft();
  }

  /**
   * Register widget to be draggable and object that will be dropped when dragging stops over a drop zone. Given widget
   * will be placed over dragging area in given left and top coordinates.
   * @param widget this widget that will be dragged.
   * @param object this object will be dropped when drag stops over drop zone.
   */
  void add(HasMouseDownHandlers widget, Object object);

  void add(Draggable draggable);

  /**
   * Add widget to drag zone view with given coordinates.
   * @param widget to be added.
   * @param left coordinate.
   * @param top coordinate.
   */
  void addWidget(Widget widget, int left, int top);

  /**
   * Add widget to drag zone.
   * @param widget to be added.
   */
  void addWidget(Widget widget);

  /**
   * Default is dragFrame
   * @param styleName style name.
   */
  void setFrameStyle(String styleName);

  /**
   * Add new Attribute to the frame style. This method adds new attribute to the frame style.
   * @param attribute attribute to be added. ex: border: 1px dotted;
   */
  void addStyleAttribute(String attribute);

  /**
   * Remove style parameter from frame.
   */
  void removeStyle();

   /**
   * Add new root who contains drop zones. This roots will be searched to find drop zones.
   * @param root widget who implements HasWidgets.
   */
  void addDropZoneRoot(HasWidgets root);

  void addDropZoneRoot(List<HasWidgets> roots);

   /**
   * Set size for a drop zone.
   * @param width in pixels;
   * @param height in pixels.
   */
  void setSize(int width, int height);

  /**
   * Set size for a drop zone.
   * @param width of the drag zone.
   * @param height of the drag zone.
   */
  void setSize(String width, String height);

  /**
   * Attach DragZone view to the parent widget.
   * @param parent widget where drag zone will be attached.
   */
  void go(HasWidgets parent);

  /**
   * Return an instance of DragZoneView.
   * @return drag zone view who is AbsolutePanel.
   */
  HasWidgets getDragZone();

  /**
   * Remove widget from drag zone.
   * @param widget to be removed.
   */
  void removeWidget(Widget widget);
}
