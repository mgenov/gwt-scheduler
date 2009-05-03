package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;

/**
 * Defines event controller operations.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IDateFactory {

  /**
   * Gets the next date period.
   * 
   * @param current the current date
   * @return the next period
   */
  IDate next(IDate current);

  /**
   * Gets the previous period.
   * 
   * @param current the curent date
   * @return the previous period
   */
  IDate previous(IDate current);

}
