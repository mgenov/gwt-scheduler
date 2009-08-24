package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.EventWidgetFactory;
import gwtscheduler.client.interfaces.LassoSubject;

import com.google.gwt.user.client.ui.Label;

/**
 * Creates event widgets.
 * @author malp
 */
public class GenericEventWidgetFactory implements EventWidgetFactory {

  @Override
  public EventWidget createEvent(LassoSubject subject, int[] from, int[] to) {
    Label label = new Label("x");
    label.getElement().getStyle().setProperty("border", "1px solid red");
    label.getElement().getStyle().setProperty("opacity", "1.0");
    label.getElement().getStyle().setProperty("filter", "alpha(opacity=100)");
    //TODO implement me
    return null;
  }

}
