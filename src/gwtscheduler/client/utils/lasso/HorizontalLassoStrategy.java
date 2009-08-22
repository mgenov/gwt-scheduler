package gwtscheduler.client.utils.lasso;

import java.util.List;

/**
 * Lasso strategy for line-axis selections.
 * @author malp
 */
public class HorizontalLassoStrategy extends GenericLassoStrategy {

  @Override
  public int compare(int[] start, int[] end) {
    if (start[0] == end[0]) {//same row
      return start[1] - end[1];
    } else {
      return start[0] - end[0];
    }
  }

  @Override
  protected List<int[]> stripInSegments(int[] from, int[] to) {
    return null;
  }

}
