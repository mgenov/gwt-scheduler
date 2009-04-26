package gwtscheduler.client.modules.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Marker interface for days related implementations.
 * 
 * @author malp
 */
// any target
// @Target( { ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Day {

}
