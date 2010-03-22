package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.client.widgets.view.common.EventsDashboard;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperView extends Composite implements CalendarEventResizeHelperImpl.Display{
  private Label label = new Label();

  public CalendarEventResizeHelperView() {
    label.getElement().setAttribute("style", "border: 1px dotted;");
    label.getElement().getStyle().setZIndex(2);
    initWidget(label);
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
  public void setCursorStyle(String style) {
//    GWT.log(style, null);
    label.getElement().getStyle().setProperty("cursor", style);
  }

  @Override
  public void release() {
    DOM.releaseCapture(label.getElement());
  }

  @Override
  public void removeFromParent(EventsDashboard.Display dashboard) {
    dashboard.asWidget().remove(this);
  }

  @Override
  public void go(EventsDashboard.Display dashboard, Integer left, Integer top) {
    dashboard.asWidget().add(this, left, top);
  }
}
