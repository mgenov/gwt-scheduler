package gwtscheduler.common.event.colors;

/**
 * Represents a factory that builds different default colors for the events
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DefaultEventColors {

  public static DefaultEventColor getRedEventColor() {
    return new DefaultRedEventColor();
  }

  public static DefaultEventColor getBlueEventColor() {
    return new DefaultBlueEventColor();
  }

  public static DefaultEventColor getGreenEventColor() {
    return new DefaultGreenEventColor();
  }

  public static DefaultEventColor getYellowEventColor() {
    return new DefaultYellowEventColor();
  }
}
