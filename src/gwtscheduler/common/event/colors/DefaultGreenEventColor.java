package gwtscheduler.common.event.colors;

import gwtscheduler.common.event.colors.DefaultColor;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultGreenEventColor implements DefaultColor{
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
