package qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;

//AnAnnotationLiteralINSTANCE.Literal.INSTANCE

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AnAnnotationLiteralINSTANCE {
	
	@SuppressWarnings("all")
    public static final class Literal extends AnnotationLiteral<AnAnnotationLiteralINSTANCE> implements AnAnnotationLiteralINSTANCE {
        private static final long serialVersionUID = 1L;
        
        public static final Literal INSTANCE = new Literal();
    }
	
}