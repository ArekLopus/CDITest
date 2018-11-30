package qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Payment {

    PaymentType value() default PaymentType.NONE;

    public enum PaymentType {
        NONE,
        VISA,
        MASTERCARD,
        WIRE_TRANSFER,
    }

}