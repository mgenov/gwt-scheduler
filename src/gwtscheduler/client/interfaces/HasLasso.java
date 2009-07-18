package gwtscheduler.client.interfaces;

/**
 * Defines a lasso component. The lasso component is responsible for drawing the
 * lasso selection on the screen.
 * @author malp
 */
public interface HasLasso {

  /**
   * Sets the lasso size.
   * @param rows the number of rows
   * @param cols the number of columns
   */
  void setGridSize(int rows, int cols);

  /**
   * Starts the lasso selection at the specified index.
   * @param startIndex the start index
   */
  void startSelection(int startIndex);

  /**
   * Updates the lasso selection to contain the end index. The end index should
   * be greater or equal to the start index. The lasso selection must have been
   * previously started.
   * @param endIndex the end index
   */
  void updateSelection(int endIndex);

  /**
   * Stops the lasso selection.
   */
  void stopSelection();
}
