package gwtscheduler.client.interfaces.decorable;

import gwtscheduler.client.interfaces.ICell;

import java.util.Iterator;

import com.google.gwt.user.client.Element;

/**
 * Defines a decorable element.
 * @author malp
 * @param <T> the decorable type
 */
public interface IHasMultipleDecorables<T extends Element> {

  //TODO make sure that the iterator is immutable
  
  /**
   * Gets the horizontal decorable elements iterator.
   * @return
   */
  Iterator<ICell<T>> getHorizontalDecorableElementsIterator();

  /**
   * Gets the vertical decorable elements iterator.
   * @return
   */
  Iterator<ICell<T>> getVerticalDecorableElementsIterator();

  /**
   * Gets the decorable elements iterator that are neither horizontal or
   * vertical, or are both at the same time.
   * @return
   */
  Iterator<ICell<T>> getMultipleDecorableElementsIterator();
}
