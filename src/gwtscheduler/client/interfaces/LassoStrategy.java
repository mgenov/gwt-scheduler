package gwtscheduler.client.interfaces;

import java.util.List;

/**
 * Defines a lasso strategy.
 * @author malp
 */
public interface LassoStrategy {
  /**
   * Gets the blocks for a given selection range.
   * @param start the start
   * @param end the end
   * @return the blocks
   */
  List<int[]> getBlocks(LassoSubject subject, int[] start, int[] end);

  /**
   * Gets the number of cells that are needed to traverse from the beggining of
   * the grid to the supplied position.
   * @param s the subject
   * @param pos the position
   * @return the absolute distance
   */
  int absoluteDistance(LassoSubject s, int[] pos);

}
