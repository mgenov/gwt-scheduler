package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface HasEventDeleteEventHandlers extends HasHandlers {

  HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler);

}
