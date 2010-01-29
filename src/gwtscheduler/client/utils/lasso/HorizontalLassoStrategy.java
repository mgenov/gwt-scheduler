package gwtscheduler.client.utils.lasso;

import gwtscheduler.client.interfaces.LassoSubject;

import java.util.ArrayList;
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
  protected List<int[]> stripInSegments(LassoSubject s, int[] from, int[] to) {
    int rows = to[0] - from[0] + 1;
    List<int[]> result = new ArrayList<int[]>(rows);
    int firstRow = from[0];
    int lastRow = to[0];

    for (int i = firstRow; i <= lastRow; i++) {
      int[] startingPoint = (i == firstRow) ? from : new int[] {i, 0};
      int[] endingPoint = (i == lastRow) ? to : new int[] {i, s.getColNum() - 1};

      result.add(startingPoint);
      result.add(endingPoint);
    }
    return result;
  }

  @Override
  public int absoluteDistance(LassoSubject s, int[] pos) {
    return (pos[0] * s.getColNum()) + pos[1];
  }

}
