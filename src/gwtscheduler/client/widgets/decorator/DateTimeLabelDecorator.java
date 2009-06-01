package gwtscheduler.client.widgets.decorator;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.interfaces.IDecorator;
import gwtscheduler.common.calendar.IDate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 * @author malp
 */
public class DateTimeLabelDecorator implements IDecorator<Element> {

  public void decorate(IDate period, ICell<Element> cell, Element element) {
    element.setInnerText(period.toString());
    GWT.log("decorating", null);
  }

}
