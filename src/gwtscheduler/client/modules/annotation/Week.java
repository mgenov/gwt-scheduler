package gwtscheduler.client.modules.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Marker interface for weeks related implementations.
 * 
 * @author malp
 */
// @Target( { ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Week {

}
