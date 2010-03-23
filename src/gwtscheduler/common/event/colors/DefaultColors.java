package gwtscheduler.common.event.colors;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DefaultColors {

  public static DefaultColor getRedEventColor() {
    return new DefaultRedEventColor();
  }

  public static DefaultColor getBlueEventColor() {
    return new DefaultBlueEventColor();
  }

  public static DefaultColor getGreenEventColor() {
    return new DefaultGreenEventColor();
  }

  public static DefaultColor getYellowEventColor() {
    return new DefaultYellowEventColor();
  }
}
