package gwtscheduler.common.event.colors;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
class DefaultRedEventColor implements DefaultColor{
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
