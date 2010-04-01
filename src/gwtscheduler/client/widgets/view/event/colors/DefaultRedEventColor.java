package gwtscheduler.client.widgets.view.event.colors;

/**
 * Red color decoration of the event
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultRedEventColor implements DefaultEventColor {
  @Override
  public String getHeaderColor() {
    return "#E36666";
  }

  @Override
  public String getBodyColor() {
    return "#FFE0E0";
  }

  @Override
  public String getTitleColor() {
    return "#FFFFFF";
  }

  @Override
  public String getTextColor() {
    return "#000000";
  }
}
