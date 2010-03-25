package gwtscheduler.common.event.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface EventResourceBundle extends ClientBundle {
  public static final EventResourceBundle INSTANCE =  GWT.create(EventResourceBundle.class);

  @Source("close.png")
  ImageResource  getClosePng();
}
