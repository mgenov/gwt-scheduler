package gwtscheduler.common.event.colors;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultYellowEventColor implements DefaultColor {
  @Override
  public String getHeaderColor() {
    return "#e2e366";
  }

  @Override
  public String getBodyColor() {
    return "#ffffe0";  
  }

  @Override
  public String getTitleColor() {
    return  "#FFFFFF";
  }

  @Override
  public String getTextColor() {
    return "#000000";
  }
}
