package gwtscheduler.client.widgets.view.columns;

import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.CalendarViewPanel;
import gwtscheduler.client.widgets.view.common.CalendarViewPanelWidget;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarColumnsFrameGrid {
  public interface Display{
    
    int getHeight();

    int getWidth();

    
    WidgetResizeHandler getWidgetResizeHandler();
  }
  public Display display;

  public CalendarColumnsFrameGrid() {
  }

  public void bindDisplay(Display  display){
    this.display = display;
  }


}
