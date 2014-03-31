package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.widgets.view.common.EventsDashboard;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class CalendarEventResizeHelperViewImpl  extends Composite implements CalendarEventResizeHelperImpl.Display{
  interface CalendarEventResizeHelperViewImplUiBinder extends UiBinder<Widget, CalendarEventResizeHelperViewImpl> {
  }

  private static CalendarEventResizeHelperViewImplUiBinder binder = GWT.create(CalendarEventResizeHelperViewImplUiBinder.class);

  @UiField
  Label frame;

  public CalendarEventResizeHelperViewImpl() {

    initWidget(binder.createAndBindUi(this));

  }

  @Override
   public HasMouseMoveHandlers getMouseMoveHandlers() {
     return frame;
   }

   @Override
   public HasMouseUpHandlers getMouseUpHandlers() {
     return frame;
   }

   @Override
   public void capture() {
     DOM.setCapture(frame.getElement());
   }

   @Override
   public void setWidth(int width) {
     frame.setWidth(width + "px");
   }

   @Override
   public void setHeight(int height) {
     frame.setHeight(height + "px");
   }

   @Override
   public int getHeight() {
     return frame.getOffsetHeight();
   }

   @Override
   public void setCursorStyle(String style) {
     frame.getElement().getStyle().setProperty("cursor", style);
   }

   @Override
   public void release() {
     DOM.releaseCapture(frame.getElement());
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
