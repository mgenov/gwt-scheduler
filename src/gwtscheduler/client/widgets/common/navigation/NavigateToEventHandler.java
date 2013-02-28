package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.EventHandler;
import gwtscheduler.common.util.DateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface NavigateToEventHandler extends EventHandler{

   void onNavigateTo(DateTime date);
}
