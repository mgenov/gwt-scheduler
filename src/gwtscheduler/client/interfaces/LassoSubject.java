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
   * Gets the number of rows.
   * @return the number of rows
   */
  int getRowNum();

  /**
   * Gets the number of columns
   * @return the number of columns
   */
  int getColNum();

  /**
   * Gets the subject's width
   * @return the width
   */
  int getWidth();

  /**
   * Gets the subject's height
   * @return the height
   */
  int getHeight();

  /**
   * Gets the lasso subject elements.
   * @return the lasso elements
   */
  List<Cell<Element>> getLassoSubjects();

}
