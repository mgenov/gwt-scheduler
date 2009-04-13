package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.CssResource;

/**
 * Common Css Resources. This will hold structural css values.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResource extends CssResource {

	/**
	 * Generic container .
	 * 
	 * @return the css class for a generic container
	 */
	String genericContainer();

	/**
	 * Generic container padding.
	 * 
	 * @return
	 */
	int genericContainerPaddingPx();
}