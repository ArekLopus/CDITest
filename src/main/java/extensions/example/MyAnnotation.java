package extensions.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	UserType value() default UserType.USER;

    public enum UserType {
        USER,
        ADMINISTRATOR,
        TESTER,
        BOSS,
    }

}