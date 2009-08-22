package gwtscheduler.client.utils.lasso;

import java.util.List;

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
  protected List<int[]> stripInSegments(int[] from, int[] to) {
    return null;
  }

}
