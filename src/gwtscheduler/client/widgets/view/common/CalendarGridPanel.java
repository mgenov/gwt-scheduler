package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarGridPanel {
  public interface Display{

    Widget getParent();

    void setPixelSize(int width, int height);

    Panel getTitleColumn();

    List<Panel> getMainColumns();

    int getColumnCount();

    int getRowCount();

    boolean isAttached();

    Element getElement();

    void removeColumn(int calendarColumnIndex);

    void addColumn(String title);
  }
}
