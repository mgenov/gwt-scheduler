package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.EventWidgetFactory;
import gwtscheduler.client.interfaces.LassoSubject;

/**
 * Creates event widgets.
 * @author malp
 */
public class GenericEventWidgetFactory extends AbstractMultiCellWidgetFactory implements EventWidgetFactory {

  @Override
  public EventWidget createEvent(LassoSubject subject, int[] from, int[] to) {
    EventWidget ew = new EventWidget();
    ew.getElement().getStyle().setProperty("border", "1px solid red");
    ew.getElement().getStyle().setProperty("opacity", "1.0");
    ew.getElement().getStyle().setProperty("filter", "alpha(opacity=100)");

    int w = getWidth(subject, to[1] - from[1] + 1);
    int h = getHeight(subject, to[0] - from[0] + 1);
    ew.setPixelSize(w, h);

    return ew;
  }


}
