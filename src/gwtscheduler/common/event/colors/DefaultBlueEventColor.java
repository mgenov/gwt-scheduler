package gwtscheduler.common.event.colors;

import gwtscheduler.common.event.colors.DefaultColor;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultBlueEventColor implements DefaultColor{
  
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
