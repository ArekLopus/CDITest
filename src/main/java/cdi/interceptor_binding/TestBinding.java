package cdi.interceptor_binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Inherited 
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) 
public @interface TestBinding {

	@SuppressWarnings("all")
    public static final class Literal extends AnnotationLiteral<TestBinding> implements TestBinding {
        private static final long serialVersionUID = 1L;
        
        public static final Literal INSTANCE = new Literal();
    }
}

//-if -> @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
//	WELD-000619: A lifecycle callback interceptor declares an interceptor binding with target other than ElementType.TYPE
//	Interceptor [class cdi.interceptor_binding.TestBindingInterceptor intercepts @TestBinding]
//	Binding: cdi.interceptor_binding.TestBinding
//	Target: [TYPE, METHOD, CONSTRUCTOR]]]