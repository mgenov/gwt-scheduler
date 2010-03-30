package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.EventHandler;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface NavigateToEventHandler extends EventHandler{

   void onNavigateTo(ReadableDateTime date);
}
