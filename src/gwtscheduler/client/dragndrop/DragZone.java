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
 * <p>
 * Interface has only one method that accept:
 * widget to be dragged, Object to be dropped when dragging stops over drop zone
 * and positions where given widget to be attached on the panel.
 * </p>
 * One simple example how this need to be used:
 *
 * <pre>
 * AbsolutePanel draggingArea = new AbsolutePanel();
 * draggingArea.setPixelSize(800, 800);
 * Draggable draggable = new DraggablePresenter(new DraggableWidget());
 *
 * DragZone dragger = new DragZoneImpl(draggingArea);
 * draggable.go(dragger, 10, 10);
 *
 * RootPanel.get().add(draggingArea);
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

    void dropTo(DropZone dropZone, int x, int y, Object targetObject);

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

  void addWidget(Widget widget, int left, int top);

   /**
    * Default is dragFrame
    * @param styleName style name.
    */
  void setFrameStyle(String styleName);

  void registerDropZoneRoot(HasWidgets root);

  void setSize(int width, int height);
  
  void go(HasWidgets parent);

  HasWidgets getDragZone();

}
