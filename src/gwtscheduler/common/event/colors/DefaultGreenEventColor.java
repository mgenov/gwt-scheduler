package gwtscheduler.common.event.colors;

import gwtscheduler.common.event.colors.DefaultEventColor;

/**
 * Green color decoration of the event
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultGreenEventColor implements DefaultEventColor {
  @Override
  public String getHeaderColor() {
    return "#7dca5b";
  }

  @Override
  public String getBodyColor() {
    return "#e3ffe0";
  }

  @Override
  public String getTitleColor() {
    return "#FFFFFF"; //white
  }

  @Override
  public String getTextColor() {
    return "#000000";  //black
  }
}
