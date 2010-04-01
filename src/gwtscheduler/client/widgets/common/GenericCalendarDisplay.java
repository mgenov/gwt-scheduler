package gwtscheduler.client.widgets.common;

import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;

import java.util.List;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic view for displaying a calendar.
 * @author malp
 */
public interface GenericCalendarDisplay extends ComplexGrid {

  /**
   * Forces the layout.
   */
  void forceLayout();

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

//  /**
//   * Gets the decorables elements.
//   * @return the decorables element
//   */
//  HasMultipleDecorables<Element> getDecorables();

  /**
   * Inits the lasso.
   * @param strat the strategy
   * @param subject the lasso subject
   */
  void initLasso(LassoStrategy strat, ComplexGrid subject);

  /**
   * Gets the number of columns.
   * @return
   */
  int getColNum();

  /**
   * Gets the number of rows.
   * @return
   */
  int getRowNum();
}
