package gwtscheduler.client.dragndrop;

import com.google.gwt.user.client.ui.Label;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
class DraggedFrame extends Draggable{
  private Label label = new Label();
  protected DraggedFrame() {
    label.setStyleName("dragFrame");
    initWidget(label);
  }

  public void setText(String text){
    label.setText(text);
  }

  public void show(){
    label.setVisible(true);
  }

  public void hide(){
    label.setVisible(false);
  }
}
