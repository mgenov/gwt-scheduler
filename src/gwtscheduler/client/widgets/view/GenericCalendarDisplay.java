package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.Cell;

import java.util.List;

import com.google.gwt.user.client.Element;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public interface GenericCalendarDisplay extends WidgetDisplay {

  /**
   * Gets the height in pixels.
   * @return
   */
  int getHeight();

  /**
   * Gets the width in pixels.
   * @return
   */
  int getWidth();

  /**
   * Gets the current visible elements.
   * @return
   */
  List<Cell<Element>> getVisibleElements();

}
