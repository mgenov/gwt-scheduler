package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

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
 * DragZone dragZone = Zones.getDragZone(800, 800);
 * dragZone.add((HasMouseDownHandlers)draggableWidget, draggablePresenter);
 * 
 * dragZone.registerDropZoneRoot(dropZonesPanel);
 *
 * dragger.go(RootPanel.get());
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

    HasMouseMoveHandlers getFrameMouseMoveHandlers();

    HasMouseUpHandlers getFrameMouseUpHandlers();

    int getSourceWidth();

    int getSourceHeight();

    void addFrameAtPosition(int left, int top);

    void captureFrame();

    void setFrameSize(int width, int height);

    void storeDragWidget(MouseDownEvent event);

    boolean isDragWidgetStored();    

    DropZone getDropZone(ArrayList<HasWidgets> roots, int x, int y);

    void fireDragInEvent(DropZone dropZone, int x, int y);

    void fireDragOutEvent(DropZone dropZone);

    void releaseFrameCapture();

    void removeFrameFromPanel();

    void dropTo(DropZone dropZone, Object targetObject, int startX, int startY, int endX, int endY);

    void setFrameStyle(String styleName);

    HasWidgets getContainer();

    void setSize(int width, int height);

    void addWidget(Widget widget, int left, int top);

    HasMouseDownHandlers getDragWidget();

    void fireDragOverEvent(DropZone dropZone, int mouseX, int mouseY);
  }

  /**
   * Register widget to be draggable and object that will be dropped when dragging stops over a drop zone. Given widget
   * will be placed over dragging area in given left and top coordinates.
   * @param widget this widget that will be dragged.
   * @param object this object will be dropped when drag stops over drop zone.
   */
  void add(HasMouseDownHandlers widget, Object object);

  /**
   * Add widget to be added on drag zone view with given coordinates.
   * @param widget to be added.
   * @param left
   * @param top
   */
  void addWidget(Widget widget, int left, int top);

   /**
    * Default is dragFrame
    * @param styleName style name.
    */
  void setFrameStyle(String styleName);

   /**
   * Add new root who contains drop zones. This roots will be searched to find drop zones.
   * @param root widget who implements HasWidgets.
   */
  void registerDropZoneRoot(HasWidgets root);

   /**
   * Set size for a drop zone.
   * @param width in pixels;
   * @param height in pixels.
   */
  void setSize(int width, int height);

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

}
