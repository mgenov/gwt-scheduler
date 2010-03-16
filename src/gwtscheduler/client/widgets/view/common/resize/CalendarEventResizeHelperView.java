package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.client.widgets.view.common.EventsDashboard;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperView extends Composite implements CalendarEventResizeHelper.Display{
  private String style = "border: 1px dotted;";
  private Label label = new Label();

  public CalendarEventResizeHelperView() {
    initWidget(label);
    label.getElement().setAttribute("style", style);
    label.getElement().getStyle().setZIndex(133);
  }

  @Override
  public HasMouseMoveHandlers getMouseMoveHandlers() {
    return label;
  }

  @Override
  public HasMouseUpHandlers getMouseUpHandlers() {
    return label;
  }

  @Override
  public void capture() {
    DOM.setCapture(label.getElement());
  }

  @Override
  public void setWidth(int width) {
    label.setWidth(width + "px");
  }

  @Override
  public void setHeight(int height) {
    label.setHeight(height + "px");
  }

  @Override
  public int getHeight() {
    return label.getOffsetHeight();
  }

  @Override
  public HandlerRegistration addResizeStartHandler(ResizeStartHandler handler, GwtEvent.Type<ResizeStartHandler> type) {
    return addHandler(handler, type);
  }

  @Override
  public HandlerRegistration addResizeEndHandler(ResizeEndHandler handler, GwtEvent.Type<ResizeEndHandler> type) {
    return addHandler(handler, type);
  }

  @Override
  public void release() {
    DOM.releaseCapture(label.getElement());
  }

  @Override
  public void go(EventsDashboard.Display dashboard) {
    dashboard.asWidget().add(this);
  }

  @Override
  public void go(EventsDashboard.Display dashboard, Integer left, Integer top) {
    dashboard.asWidget().add(this, left, top);
  }
}
