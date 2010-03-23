package gwtscheduler.client.widgets.common.decoration;

import gwtscheduler.client.widgets.common.Cell;

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
  List<Cell<T>> getDecorableElements();
}
