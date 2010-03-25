package gwtscheduler.common.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.common.event.images.EventResourceBundle;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEventView extends Composite implements CalendarEvent.Display {
  public interface ResStyle extends CssResource {
    String roundedPanel();

    String closeBtn();
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
  Image closeBtn;


  public CalendarEventView() {
    initWidget(uiBinder.createAndBindUi(this));
    this.getElement().getStyle().setZIndex(1);
    this.eventHeader.getElement().getStyle().setCursor(Style.Cursor.MOVE);
    roundedPanel.addStyleName(resStyle.roundedPanel());
    closeBtn.setResource(EventResourceBundle.INSTANCE.getClosePng());
    closeBtn.setStyleName(resStyle.closeBtn());
  }

  @UiFactory
  public RoundedLinePanel buildRoundedLinePanel(){
    RoundedLinePanel roundedPanel = new RoundedLinePanel(RoundedPanel.TOP, 3);
    roundedPanel.setCornerColor("#6694E3");
    return roundedPanel;
  }

  @Override
  public void setViewWidth(int width) {
    htmlPanel.setWidth((width - 4 ) + "px");
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
  public HasClickHandlers getBody() {
    return contentPanel;
  }

  @Override
  public void setDescription(String description) {
    contentPanel.setText(description);
  }

  @Override
  public void setHeaderColor(String headerColor) {
    eventHeader.getElement().getStyle().setBackgroundColor(headerColor);
    htmlPanel.getElement().getStyle().setBorderColor(headerColor);
    roundedPanel.setCornerColor(headerColor);
  }

  @Override
  public void setBodyColor(String bodyColor) {
    htmlPanel.getElement().getStyle().setBackgroundColor(bodyColor);
  }

  @Override
  public void setTitleColor(String titleColor) {
    eventHeader.getElement().getStyle().setColor(titleColor);
  }

  @Override
  public void setTextColor(String textColor) {
    contentPanel.getElement().getStyle().setColor(textColor);
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
