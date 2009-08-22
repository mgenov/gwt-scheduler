package gwtscheduler.client.utils;

/**
 * Utility class for points operations.
 * @author malp
 */
public class PointUtils {
  /**
   * Compares two positions.
   * @param p1 the first position
   * @param p2 the second position
   * @return <code>true</code> if the two positions are the same
   */
  public static boolean equals(int[] p1, int[] p2) {
    assert p1 != null : "Cannot compare a null position";
    assert p2 != null : "Cannot compare a null position";

    assert p1.length == 2 : "Position length != 2";
    assert p2.length == 2 : "Position length != 2";

    return (p1[0] == p2[0] && p1[1] == p2[1]);
  }
}
