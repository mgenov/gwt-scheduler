package gwtscheduler.client.interfaces;

import gwtscheduler.client.interfaces.decorable.IHasMultipleDecorables;

import org.goda.time.Interval;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic decorator.
 * @author malp
 * @param <T> the decorable
 */
public interface IDecorator<T extends Element> {

  /**
   * Fired when the decorator should decorate elements.
   * @param interval the interval
   * @param decorable the decorable
   */
  void decorate(Interval interval, IHasMultipleDecorables<Element> decorable);
}
