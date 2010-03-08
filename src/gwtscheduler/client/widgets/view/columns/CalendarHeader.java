package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.ui.FlexTable;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarHeader {

 

  public interface Display{

    void buildCalendarHeader(int columns);

  }

  private Display display;


  public CalendarHeader() {
  }

  public void bindDisplay(Display display){
    this.display = display;
  }


}

