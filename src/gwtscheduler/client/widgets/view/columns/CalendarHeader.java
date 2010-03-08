package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarHeader {

 

  public interface Display{

    void removeCell(int index);

    void addCell(String title);

    WidgetResizeHandler getCalendarHeaderResizeHandler();


    HasMultipleDecorables getDecorables();
  }

  private Display display;


  public CalendarHeader() {
  }

  public void bindDisplay(Display display){
    this.display = display;
  }


  public void removeColumnHeader(int index) {
    display.removeCell(index+1);
  }

  public void addColumnHeader(String title) {
    display.addCell(title);
  }

  public WidgetResizeHandler getCalendarHeaderResizeHandler() {
    return display.getCalendarHeaderResizeHandler();
  }

  public HasMultipleDecorables getDecorables() {
    return display.getDecorables();
  }

  public List<Cell<Element>> getHeaderDecorableElements() {
    return display.getDecorables().getDecorableElements();
  }
}

