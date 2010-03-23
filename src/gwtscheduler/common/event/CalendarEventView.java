package gwtscheduler.common.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEventView extends Composite implements CalendarEvent.Display {
  public interface ResStyle extends CssResource {
    String roundedPanel();
  }

  interface CalendarEventViewBinder extends UiBinder<Widget, CalendarEventView> {};

  private static CalendarEventViewBinder uiBinder = GWT.create(CalendarEventViewBinder.class);

  @UiField
  ResStyle resStyle;

  @UiField
  HTMLPanel htmlPanel;

  @UiField
  RoundedLinePanel roundedPanel;

  @UiField
  Label eventHeader;

  @UiField
  Label contentPanel;

  @UiField
  Label eventFooter;

  @UiField
  PushButton closeBtn;


  public CalendarEventView() {
    initWidget(uiBinder.createAndBindUi(this));
    this.getElement().getStyle().setZIndex(1);
    this.eventHeader.getElement().getStyle().setCursor(Style.Cursor.MOVE);
    this.eventFooter.getElement().setInnerHTML("<div style=\"border-top:3px double #6694E3; width: 10px; margin: 0 auto;\"></div>");
    roundedPanel.addStyleName(resStyle.roundedPanel());
  }

  @UiFactory
  public PushButton buildPushButton(){
    return new PushButton(new Image("../css/images/close.png")); //TODO: change the url for the javascript ->  css/images/close.png
  }

  @UiFactory
  public RoundedLinePanel buildRoundedLinePanel(){
    RoundedLinePanel roundedPanel = new RoundedLinePanel(RoundedPanel.TOP, 3);
    roundedPanel.setCornerColor("#6694E3");
    return roundedPanel;
  }

  @Override
  public void setViewWidth(int width) {
    htmlPanel.setWidth((width - 4 )+ "px");
  }

  @Override
  public void setViewHeight(int height) {
    htmlPanel.setHeight((height - 4) + "px");
  }

  @Override
  public int getWidth() {
    return htmlPanel.getOffsetWidth();
  }

  @Override
  public int getHeight() {
    return htmlPanel.getOffsetHeight();
  }

  @Override
  public HasMouseDownHandlers getFooter() {
    return eventFooter;
  }

  @Override
  public HasClickHandlers getCloseBtn() {
    return closeBtn;
  }

  @Override
  public void setDescription(String description) {
    contentPanel.setText(description);
  }

  @Override
  public HasMouseDownHandlers getHeader() {
    return eventHeader;
  }

  @Override
  public void setHeaderTitle(String title) {
    eventHeader.setText(title);
  }

}
