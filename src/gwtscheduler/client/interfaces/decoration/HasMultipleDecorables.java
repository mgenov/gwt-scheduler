package gwtscheduler.client.interfaces.decoration;

import gwtscheduler.client.interfaces.Cell;

import java.util.List;

import com.google.gwt.user.client.Element;

/**
 * Defines a decorable element.
 * @author malp
 * @param <T> the decorable type
 */
public interface HasMultipleDecorables<T extends Element> {

  /**
   * Gets the horizontal decorable elements iterator.
   * @return the elements. The list should be unmodifiable
   */
  List<Cell<T>> getDaysDecorableElements();

  /**
   * Gets the vertical decorable elements iterator. These elements represent some time instance within the same day.
   * @return the elements. The list should be unmodifiable
   */
  List<Cell<T>> getWithinDayDecorableElements();

  /**
   * Gets the decorable elements iterator that are neither horizontal or vertical, or are both at the same time.
   * @return the elements. The list should be unmodifiable
   */
  List<Cell<T>> getMultipleDecorableElements();
}
