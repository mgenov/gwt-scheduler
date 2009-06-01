package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic decorator.
 * @author malp
 * @param <T> the decorable
 */
public interface IDecorator<T extends Element> {

  /**
   * Decorates an element.
   * @param date the time period
   * @param cell the cell
   * @param element the decorable element
   */
  void decorate(IDate date, ICell<T> cell, T element);
}
