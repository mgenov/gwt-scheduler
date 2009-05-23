package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.CssResource;

/**
 * Common Css Resources. This will hold structural css values.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface DayWeekCssResource extends CommonCssResource, CssResource {

  /**
   * Defines a small css border size.
   * @return the border
   */
  int smallBorderPx();

  /**
   * @return
   */
  int smallPaddingPx();

  /**
   * Defines a medium left css border size.
   * @return the border
   */
  int mediumBorderPx();

  /**
   * Defines width of first column (title column)
   * @return the width
   */
  int titleColumnWidthPx();

  /**
   * Even cell class.
   * @return the css class name
   */
  String evenCell();

  /**
   * Odd cell class.
   * @return the css class name
   */
  String oddCell();

  /**
   * Column class.
   * @return the class name for columns
   */
  String column();

  /**
   * Title column class.
   * @return the title column name
   */
  String titleColumn();

  /**
   * Css class for horizontal grid.
   * @return css class name
   */
  String horizontalFillGrid();

  /**
   * Class name for event title cell.
   * @return the class name
   */
  String evenTitleCell();

  /**
   * Class name for odd title cell.
   * @return the class name
   */
  String oddTitleCell();
}