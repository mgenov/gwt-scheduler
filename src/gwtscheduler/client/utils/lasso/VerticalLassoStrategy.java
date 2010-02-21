package gwtscheduler.client.utils.lasso;

import gwtscheduler.client.widgets.common.ComplexGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Lasso strategy for page-axis selections.
 * @author malp
 */
public class VerticalLassoStrategy extends GenericLassoStrategy {

  /** indicates if can select more than one column */
  private boolean isMultiColumn = true;

  /**
   * Default constructor.
   */
  public VerticalLassoStrategy() {
    super();
  }

  /**
   * Optional constructor for defining multi-column mode.
   * @param isMultiColumn <code>true</code> if multi column selection is to be
   *          available
   */
  public VerticalLassoStrategy(boolean isMultiColumn) {
    this();
    this.isMultiColumn = isMultiColumn;
  }

  /**
   * Indicates if the strategy can select more than one column
   * @return <code>true</code> if is multi column
   */
  public boolean isMultiColumn() {
    return isMultiColumn;
  }

  /**
   * Sets the multi column mode.
   * @param isMultiColumn the mode
   */
  public void setMultiColumn(boolean isMultiColumn) {
    this.isMultiColumn = isMultiColumn;
  }

  @Override
  public int compare(int[] start, int[] end) {
    if (start[1] == end[1]) {//same col
      return start[0] - end[0];
    } else {
      return start[1] - end[1];
    }
  }

  @Override
  protected List<int[]> stripInSegments(ComplexGrid s, int[] from, int[] to) {
    int colspan = to[1] - from[1] + 1;//colspan
    List<int[]> result = new ArrayList<int[]>(colspan);
    int firstCol = from[1];
    int lastCol = isMultiColumn ? to[1] : from[1];

    int[] lastElem = to[1] == lastCol ? to : new int[] {to[0], lastCol};

    for (int i = firstCol; i <= lastCol; i++) {
      int[] startingPoint = (i == firstCol) ? from : new int[] {0, i};
      //if last col and multi, return bound
      // if not multi, return last row in same col
      int[] endingPoint = (i == lastCol) ? lastElem : new int[] {s.getRowNum() - 1, i};

      result.add(startingPoint);
      result.add(endingPoint);
    }
    return result;
  }

  @Override
  public int absoluteDistance(ComplexGrid s, int[] pos) {
    return (pos[1] * s.getRowNum()) + pos[0];
  }

}
