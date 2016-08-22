package name.ilab.playground.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by cuijfboy on 16/8/22.
 */
@Target(TYPE)
@Retention(CLASS)
public @interface Playground {
    String value() default "";
}
