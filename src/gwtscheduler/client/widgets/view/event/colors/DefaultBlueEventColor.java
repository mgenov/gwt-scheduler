package gwtscheduler.client.widgets.view.event.colors;

import gwtscheduler.client.widgets.view.event.colors.DefaultEventColor;

/**
 * Blue color decoration of the event
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultBlueEventColor implements DefaultEventColor {
  
  @Override
  public String getHeaderColor() {
    return "#6694E3";
  }

  @Override
  public String getBodyColor() {
    return " #E0ECFF";
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
