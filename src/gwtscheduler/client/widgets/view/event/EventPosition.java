package gwtscheduler.client.widgets.view.event;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EventPosition {

  private Integer left;
  private Integer top;

  public EventPosition() {
  }

  public EventPosition(Integer left, Integer top) {
    this.left = left;
    this.top = top;
  }

  public Integer getLeft() {
    return left;
  }

  public void setLeft(Integer left) {
    this.left = left;
  }

  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
  }
}
