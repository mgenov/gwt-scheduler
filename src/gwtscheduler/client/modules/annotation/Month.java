package gwtscheduler.client.modules.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Marker interface for months related implementations.
 * 
 * @author malp
 */
@Retention(RetentionPolicy.RUNTIME)
// @Target( { ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@BindingAnnotation
public @interface Month {

}
