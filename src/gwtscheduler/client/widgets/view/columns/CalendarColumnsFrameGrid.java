package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.List;

/**
 * Represents  the view of the grid cells
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarColumnsFrameGrid {
  public interface Display{
    
    int getHeight();

    int getWidth();

    WidgetResizeHandler getWidgetResizeHandler();

    HasMultipleDecorables getDecorables();

    List<Cell<Element>> getContentDecorableElements();

    int getRows();

    int getColumns();
  }
  
  public Display display;

  public CalendarColumnsFrameGrid() {
  }

  public void bindDisplay(Display  display){
    this.display = display;
  }

  public List<Cell<Element>> getTimeLineDecorables() {
    return display.getDecorables().getDecorableElements();
  }
}
