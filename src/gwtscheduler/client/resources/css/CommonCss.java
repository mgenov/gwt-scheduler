package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.CssResource;

/**
 * Common Css Resources. This will hold structural css values.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCss extends CssResource {

    /**
     * Defines a small css border size.
     * 
     * @return the border
     */
    int smallPx();

    /**
     * Defines a medium left css border size.
     * 
     * @return the border
     */
    int mediumPx();

    /**
     * Even cell class.
     * 
     * @return the css class name
     */
    String evenCell();

    /**
     * Odd cell class.
     * 
     * @return the css class name
     */
    String oddCell();
}