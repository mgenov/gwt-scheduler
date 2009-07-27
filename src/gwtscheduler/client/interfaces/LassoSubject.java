package gwtscheduler.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.Element;

/**
 * Defines a lasso component. The lasso component is responsible for drawing the
 * lasso selection on the screen.
 * @author malp
 */
public interface LassoSubject {

  /**
   * Gets the lasso grid size.
   * @return the lasso grid size
   */
  int[] getLassoGridSize();

  /**
   * Gets the lasso subject elements.
   * @return the lasso elements
   */
  List<Cell<Element>> getLassoSubjects();

}
