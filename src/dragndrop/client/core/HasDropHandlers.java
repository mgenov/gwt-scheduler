package dragndrop.client.core;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A widget that implements this interface provides registration for
 * {@link dragndrop.client.core.DropHandler} instances.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface HasDropHandlers extends HasHandlers{

  /**
   * Adds a {@link dragndrop.client.core.DropHandler} handler.
   *
   * @param handler the blur handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addDropHandler(DropHandler handler);
}
