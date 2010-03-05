package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class DragZoneView extends Composite implements DragZone.Display {

  private AbsolutePanel absolutePanel = new AbsolutePanel();
  private Label frame = new Label();
  private Widget dragWidget;
  private Widget dropZone;

  public DragZoneView() {
    initWidget(absolutePanel);

    frame.setStyleName("dragFrame");
  }

  @Override
  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return frame;
  }

  @Override
  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return frame;
  }

  @Override
  public void setLeft(int left) {

  }

  @Override
  public void setTop(int top) {

  }

  @Override
  public int getOffsetWidth(MouseDownEvent event) {
    return 0;
  }

  @Override
  public int getOffsetHeight(MouseDownEvent event) {
    return 0;
  }

  @Override
  public void addFrameAtPosition(int left, int top) {
    absolutePanel.add(frame, left, top);
  }

  @Override
  public void captureFrame() {
    DOM.setCapture(frame.getElement());
  }

  @Override
  public void setFrameSize(int width, int height) {
    frame.setPixelSize(width, height);
  }

  @Override
  public void storeDragWidget(MouseDownEvent event) {
    dragWidget = (Widget) event.getSource();
  }

  @Override
  public boolean isDragWidgetStored() {
    return dragWidget != null;
  }

  @Override
  public DropZone getDropZone(int x, int y) {
     for (int i = 0; i < absolutePanel.getWidgetCount(); i++) {
      Widget dropWidget = absolutePanel.getWidget(i);
      if (dropWidget instanceof DropZone && dropWidget != dragWidget) {
        if (checkPosition(x, y, dropWidget)) {
          this.dropZone = dropWidget;          
          DropZone zone = (DropZone) dropWidget;
          return zone;
        }
      }
    }
    return null;
  }

  @Override
  public void fireDragOverEvent(int x, int y) {
    DragOverEvent dragOver = new DragOverEvent(frame, x, y);
    dragOver.fire(dropZone);
  }

  @Override
  public void fireDragOutEvent() {
    DragOutEvent dragOut = new DragOutEvent(frame);
    this.dropZone.fireEvent(dragOut);
    this.dropZone = null;
  }

  @Override
  public void releaseFrameCapture() {
     DOM.releaseCapture(frame.getElement());
  }

  @Override
  public void removeFrameFromPanel() {
    absolutePanel.remove(frame);
  }

  @Override
  public void dropTo(int x, int y, Object targetObject) {
     DropEvent dropEvent = new DropEvent(dragWidget, targetObject);
    // here can be implemented logic that add more data in the event.
    // data like coordinates on draggable widget, parent of draggable widget and stuff like that.
    dropEvent.setMouseX(x);
    dropEvent.setMouseY(y);
    dropEvent.fire(dropZone);
  }

  @Override
  public void setFrameStyle(String styleName) {
    frame.setStyleName(styleName);
  }

  @Override
  public void attachWidgetToDragWrapper(Widget widget, int left, int top) {      


  }

  @Override
  public HasWidgets getContainer() {
    return absolutePanel;
  }

  @Override
  public void setPosition(DragWrapperImpl wrapper, int left, int top) {
    absolutePanel.setWidgetPosition(wrapper, left, top);
  }

  /**
   * Check if given point with coordinates is in given widget.
   *
   * @param x left mouse coordinate.
   * @param y top mouse coordinate.
   * @param w widget who need to be checked.
   * @return true if given point is over widget area.
   */
  private boolean checkPosition(int x, int y, Widget w) {
    // TODO: make better logic for checking if point
    int widgetX = w.getAbsoluteLeft();
    int widgetY = w.getAbsoluteTop();
    int width = w.getElement().getClientWidth();
    int height = w.getElement().getClientHeight();

    if ((x >= widgetX && x <= widgetX + width) && (y >= widgetY && y <= widgetY + height)) {
      return true;
    }
    return false;
  }
}
