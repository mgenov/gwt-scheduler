package gwtscheduler.client.widgets.common.decoration;

import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import org.goda.time.Interval;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic decorator.
 * @author malp
 */
//FIXME: Gin doesn't like generic interfaces...
public interface MultipleElementsIntervalDecorator/* <T extends Element> */{

  /**
   * Fired when the decorator should decorate elements.
   * @param interval the time interval
   * @param decorable the decorable
   */
  void decorate(Interval interval, ColumnTitleProvider titleProvider, HasMultipleDecorables<Element> decorable);



  
}
