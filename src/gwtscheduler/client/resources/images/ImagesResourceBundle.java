package gwtscheduler.client.resources.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface ImagesResourceBundle extends ClientBundle {
  public static final ImagesResourceBundle INSTANCE =  GWT.create(ImagesResourceBundle.class);

  @Source("close.png")
  ImageResource  getClosePng();
}
