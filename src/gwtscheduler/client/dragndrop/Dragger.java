package gwtscheduler.client.dragndrop;

import com.google.gwt.user.client.ui.Widget;

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
 * Dragger dragger = new DraggerImpl(draggingArea);
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
public interface Dragger {

  /**
   * Register widget to be draggable and object that will be dropped when dragging stops over a drop zone. Given widget
   * will be placed over dragging area in given left and top coordinates.
   * @param widget this widget that will be dragged.
   * @param object this object will be dropped when drag stops over drop zone.
   * @param left coordinate from left .
   * @param top coordinate from top.
   */
  public void add(Widget widget, Object object, int left, int top);

  /**
   * Set style name of the frame that is dragged instead of draggable widget.
   * Default is dragFrame
   * @param styleName style name.
   */
  public void setFrameStyle(String styleName);

}
