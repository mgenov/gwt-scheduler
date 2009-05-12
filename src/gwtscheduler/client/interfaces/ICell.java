package gwtscheduler.client.interfaces;

import com.google.gwt.user.client.Element;

/**
 * Interface for calendar cells.
 * 
 * @author malp
 * 
 * @param <T> the underlying type
 */
public interface ICell<T extends Element> {

  /**
   * Gets the cell row.
   * 
   * @return the cell row
   */
  int row();

  /**
   * Gets the cell column.
   * 
   * @return the column
   */
  int column();

  /**
   * Gets the underlying element.
   * 
   * @return the element
   */
  T getElement();

}
