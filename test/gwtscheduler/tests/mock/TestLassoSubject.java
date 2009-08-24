package gwtscheduler.tests.mock;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;

import java.util.List;

import org.goda.time.Interval;

import com.google.gwt.user.client.Element;

/**
 * Utilit class for lasso tests.
 * @author malp
 */
public class TestLassoSubject implements LassoSubject {

  final int rows, cols;
  final int w = 100, h = 100;

  /**
   * Creates a new mock lasso subject, with a 100px x 100px grid.
   * @param rows the number of rows
   * @param cols the number of cols
   */
  public TestLassoSubject(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public int getColNum() {
    return cols;
  }

  @Override
  public List<Cell<Element>> getLassoSubjects() {
    return null;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public int getWidth() {
    return w;
  }

  @Override
  public int getHeight() {
    return h;
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    return null;
  }

}