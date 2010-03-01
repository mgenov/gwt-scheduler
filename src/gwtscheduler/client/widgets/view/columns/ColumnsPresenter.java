package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.EventNavigationListener;
import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsPresenter implements CalendarPresenter{
  @Override
  public String getTabLabel() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public EventNavigationListener getNavigationListener() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Widget getWidgetDisplay() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void forceLayout() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  
}
