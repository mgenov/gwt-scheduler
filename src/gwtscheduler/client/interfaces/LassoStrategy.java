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
  public List<int[]> getBlocks(LassoSubject subject, int[] start, int[] end);
}
