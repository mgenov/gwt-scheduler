package gwtscheduler.client.resources.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Common Css Resources. This will hold structural css values.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
interface CommonCssResource extends CssResource {

  /**
   * Generic container .
   * @return the css class for a generic container
   */
  String genericContainer();

  /**
   * @return
   */
  String lassoPanel();

  /**
   * @return
   */
  String lassoElement();

  /**
   * Generic container padding.
   * @return
   */
  int genericContainerPaddingPx();

  int scrollOffSetPx();

  @ClassName("headerPaddingLeft")
  int headerPaddingLeft();

  String genericHeaderContainer();
}