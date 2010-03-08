package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.ui.FlexTable;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarHeader {

 

  public interface Display{

    void removeCell(int index);

    void addCell(String title);

    WidgetResizeHandler getCalendarHeaderResizeHandler();
  }

  private Display display;


  public CalendarHeader() {
  }

  public void bindDisplay(Display display){
    this.display = display;
  }


  public void removeColumn(int index) {
    display.removeCell(index+1);
  }

  public void addColumnHeader(String title) {
    display.addCell(title);
  }

  public WidgetResizeHandler getCalendarHeaderResizeHandler() {
    return display.getCalendarHeaderResizeHandler();
  }
}

