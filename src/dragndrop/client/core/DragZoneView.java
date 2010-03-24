package dragndrop.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Miroslav Genov (mgenov@gmail.com) 
 */
class DragZoneView extends Composite implements DragZone.Display {

  private AbsolutePanel absolutePanel = new AbsolutePanel();
  private Widget dragWidget;

  public DragZoneView() {
    initWidget(absolutePanel);
  }

  @Override
  public int getSourceWidth() {
    if(dragWidget == null){
      return 0;
    }
    return dragWidget.getOffsetWidth();
  }

  @Override
  public int getSourceHeight() {
    if(dragWidget == null){
      return 0;
    }
    return dragWidget.getOffsetHeight();
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
  public DropZone getDropZone(ArrayList<HasWidgets> roots, int x, int y) {
    DropZone dropZone = null;
    for(HasWidgets widget : roots){
      dropZone = getDropZone(widget, x, y);
      if(dropZone != null){
        return dropZone;
      }
    }
    return dropZone;
  }

  private DropZone getDropZone(HasWidgets root, int x, int y){
    DropZone dropZone = null;
    for(Widget widget : root){

      if (widget instanceof DropZone && checkPosition(x, y, widget)) {
        return (DropZone) widget;
      }

      if(widget instanceof HasWidgets){
        dropZone = getDropZone((HasWidgets)widget, x, y);
        if(dropZone != null){
          return dropZone;
        }
      }
      
    }
    return dropZone;
  }

  @Override
  public DropZone findDropZone(ArrayList<DropZone> dropZones, int x, int y){
    for(DropZone dropZone : dropZones){
      if(dropZone instanceof Widget && checkPosition(x, y, (Widget) dropZone)){
        return dropZone;
      }
    }
    return null;
  }

  @Override
  public void fireEvent(DropZone dropZone, GwtEvent<? extends EventHandler> event) {
    ((Widget)dropZone).fireEvent(event);
  }

  @Override
  public void dropTo(DropZone dropZone, Object targetObject, int startX, int startY, int endX, int endY) {
    dropTo(dropZone, dragWidget, targetObject, startX, startY, endX,endY);
  }

  @Override
  public void dropTo(DropZone dropZone, Widget sourceWidget, Object dropObject, int startX, int startY, int endX, int endY) {
    DropEvent dropEvent = new DropEvent(sourceWidget, dropObject, new int[]{startX, startY}, new int[]{endX, endY});
    fireEvent(dropZone, dropEvent);
  }

  @Override
  public HasWidgets getContainer() {
    return absolutePanel;
  }

  @Override
  public void setSize(String width, String height){
    absolutePanel.setWidth(width);
    absolutePanel.setHeight(height);
  }

  @Override
  public boolean removeWidget(Widget widget) {
    return absolutePanel.remove(widget);
  }

  @Override
  public int getSourceTop() {
    return dragWidget.getAbsoluteTop();
  }

  @Override
  public int getSourceLeft() {
    return dragWidget.getAbsoluteLeft();
  }

  @Override
  public int getLeft() {
    return absolutePanel.getAbsoluteLeft();
  }

  @Override
  public int getTop() {
    return absolutePanel.getAbsoluteTop();
  }

  @Override
  public void addFrame(Frame frame, int left, int top) {
    addWidget(frame.getWidget(), left, top);
  }

  @Override
  public void removeFrame(Frame frame) {
    removeWidget(frame.getWidget());
  }

  @Override
  public void changeAbsolutePanel(AbsolutePanel panel) {
    absolutePanel = panel;
  }

  @Override
  public void addWidget(Widget widget, int left, int top) {
    absolutePanel.add(widget, left, top);
  }

  @Override
  public HasMouseDownHandlers getDragWidget() {
    return (HasMouseDownHandlers)dragWidget;
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
