package gwtscheduler.client.modules.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Marker interface for days related implementations.
 * @author malp
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Day {

}
