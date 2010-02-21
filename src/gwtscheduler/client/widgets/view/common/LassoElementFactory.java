package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.common.ComplexGrid;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author malp
 */
public interface LassoElementFactory {

  /**
   * Creates an event widget
   * @param subject the subject
   * @param from the from position
   * @param to the to position
   * @return
   */
  Widget createLassoElement(ComplexGrid subject, int[] from, int[] to);

}
