package gwtscheduler.common.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DListElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
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
  Image closeBtn;


  public CalendarEventView() {
    initWidget(uiBinder.createAndBindUi(this));
    this.getElement().getStyle().setZIndex(1);
    this.eventHeader.getElement().getStyle().setCursor(Style.Cursor.MOVE);
    roundedPanel.addStyleName(resStyle.roundedPanel());
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
