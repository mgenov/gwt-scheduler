package gwtscheduler.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTaskDialogWidget extends Composite implements TestTaskDialog.Display {

  /**
   * ui binder instance
   */
  private static TestTaskDialogWidgetUiBinder uiBinder = GWT.create(TestTaskDialogWidgetUiBinder.class);

  /**
   * ui binder interface
   */
  interface TestTaskDialogWidgetUiBinder extends UiBinder<Widget, TestTaskDialogWidget> {
  }

  @UiField
  TextInputFieldWidget columnTitle;
  @UiField
  TextInputFieldWidget title;
  @UiField
  TextInputFieldWidget startDate;
  @UiField
  TextInputFieldWidget startHour;
  @UiField
  TextInputFieldWidget endDate;
  @UiField
  TextInputFieldWidget endHour;
  @UiField
  TextInputAreaWidget description;

  @UiField
  DialogBox dialog;

  @UiField
  Button ok;
  @UiField
  Button cancel;


  public TestTaskDialogWidget() {
    initWidget(uiBinder.createAndBindUi(this));
    dialog.getElement().getStyle().setZIndex(100);
    columnTitle.setEnabled(false);
  }

  @Override
  public void setTaskStart(int dayOfMonth, int monthOfYear, int year, int hourOfDay, int minuteOfHour) {
    startDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
    startHour.setText(hourOfDay+":"+minuteOfHour);
  }

  @Override
  public void setTaskEnd(int dayOfMonth, int monthOfYear, int year, int hourOfDay, int minuteOfHour) {
    endDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
    endHour.setText(hourOfDay+":"+minuteOfHour);
  }

  @Override
  public void setDescription(String description) {
    this.description.setText(description);
  }

  @Override
  public void show() {
    dialog.setGlassEnabled(true);
    dialog.setAnimationEnabled(true);
    dialog.center();
    dialog.show();
    
  }

  @Override
  public HasClickHandlers getCancelBtn() {
    return cancel;
  }

  @Override
  public void close() {
    dialog.hide();
  }

  @Override
  public HasClickHandlers getOKButton() {
    return ok;
  }

  @Override
  public void setTitle(String title) {
    this.title.setText(title);
  }

  @Override
  public void setColumnTitle(String title) {
    columnTitle.setText(title);
  }

  @Override
  public String getTaskTitle() {
    return title.getText();
  }

  @Override
  public String getDescription() {
    return description.getText();
  }

  @Override
  public int getEndHour() {
    return Integer.parseInt(endHour.getText());
  }

  @Override
  public int getStartHour() {
     return Integer.parseInt(startHour.getText()); 
  }
}
