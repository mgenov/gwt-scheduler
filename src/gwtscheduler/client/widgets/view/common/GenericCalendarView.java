package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.view.common.lasso.LassoAwarePanel;

import java.util.List;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic view for displaying a calendar.
 * @author malp
 */
public interface GenericCalendarView extends WidgetDisplay {

  /**
   * Gets the height in pixels.
   * @return the height in px
   */
  int getHeight();

  /**
   * Gets the width in pixels.
   * @return the width in px
   */
  int getWidth();

  /**
   * Gets the current visible elements.
   * @return a list of visible elements
   */
  List<Cell<Element>> getVisibleElements();

  /**
   * Gets the decorables elements.
   * @return the decorables element
   */
  HasMultipleDecorables<Element> getDecorables();

  /**
   * Gets the lasso panel.
   * @return the lasso panel
   */
  LassoAwarePanel asLassoPanel();
}
