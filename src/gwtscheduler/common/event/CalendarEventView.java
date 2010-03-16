package gwtscheduler.common.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DListElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEventView extends Composite implements CalendarEvent.Display {
  interface CalendarEventViewBinder extends UiBinder<Widget, CalendarEventView> {};
  private static CalendarEventViewBinder uiBinder = GWT.create(CalendarEventViewBinder.class);

  @UiField
  RoundedLinePanel roundedPanel;

  @UiField
  Label eventHeader;

  @UiField
  SimplePanel contentPanel;

  @UiField
  Label eventFooter;

  public CalendarEventView() {
    initWidget(uiBinder.createAndBindUi(this));
//    contentPanel.add(new Label("test1"));
//    contentPanel.add(new Label("test2"));
//    contentPanel.add(new Label("test3"));
//    contentPanel.add(new Label("test4"));
//    contentPanel.add(new Label("test5"));
//    eventFooter.setUrl("http://www.google.com/images/logo.gif");
    this.getElement().getStyle().setZIndex(33);
  }

  @UiFactory
  public RoundedLinePanel buildRoundedLinePanel(){
    return new RoundedLinePanel(RoundedPanel.ALL, 2);
  }

  @Override
  public void setViewWidth(int width) {
    roundedPanel.setWidth(width + "px");
  }

  @Override
  public void setViewHeight(int height) {
    roundedPanel.setHeight(height + "px");
  }

  @Override
  public int getWidth() {
    return roundedPanel.getOffsetWidth();
  }

  @Override
  public int getHeight() {
    return roundedPanel.getOffsetHeight();
  }

  @Override
  public HasMouseDownHandlers getFooterMouseDownHandler() {
    return eventFooter;
  }

//  @Override
//  public HasMouseMoveHandlers getFooterMouseMoveHandler() {
//    return eventFooter;
//  }
//
//  @Override
//  public HasMouseUpHandlers getFooterMouseUpHandler() {
//    return eventFooter;
//  }

  @Override
  public HasMouseDownHandlers getHeader() {
    return eventHeader;
  }

  @Override
  public void setHeaderTitle(String title) {
    eventHeader.setText(title);
  }

  @Override
  public void captureFooter() {
    DOM.setCapture(eventFooter.getElement());
  }

  @Override
  public void releaseFooter() {
    DOM.releaseCapture(eventFooter.getElement());
  }
}
