package gwtscheduler.client.utils.lasso;

import gwtscheduler.client.interfaces.LassoSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Lasso strategy for page-axis selections.
 * @author malp
 */
public class VerticalLassoStrategy extends GenericLassoStrategy {

  @Override
  public int compare(int[] start, int[] end) {
    if (start[1] == end[1]) {//same col
      return start[0] - end[0];
    } else {
      return start[1] - end[1];
    }
  }

  @Override
  protected List<int[]> stripInSegments(LassoSubject s, int[] from, int[] to) {
    int cols = to[1] - from[1] + 1;
    List<int[]> result = new ArrayList<int[]>(cols);
    int firstCol = from[1];
    int lastCol = to[1];

    for (int i = firstCol; i <= lastCol; i++) {
      int[] startingPoint = (i == firstCol) ? from : new int[] {0, i};
      int[] endingPoint = (i == lastCol) ? to
          : new int[] {s.getRowNum() - 1, i};

      result.add(startingPoint);
      result.add(endingPoint);
    }
    return result;
  }

}
