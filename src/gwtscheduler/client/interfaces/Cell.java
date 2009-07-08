package gwtscheduler.client.interfaces;

import com.google.gwt.user.client.Element;

/**
 * Interface for calendar cells.
 * @author malp
 * @param <T> the underlying type
 */
public interface Cell<T extends Element> {

  /**
   * Gets the cell row.
   * @return the cell row
   */
  int row();

  /**
   * Gets the cell column.
   * @return the column
   */
  int column();

  /**
   * Gets the cell element.
   * @return the cell element
   */
  Element getCellElement();

  /**
   * Gets the index of this cell. The index is given by
   * <code>(row * rowsize) + col</code>
   * @param rowsize the rowsize
   * @return the index
   */
  int index(int rowsize);

}
