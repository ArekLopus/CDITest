package extensions.example;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

import cdi.interceptor_binding.TestBinding;

public class SimpleProcessAnnotatedTypeTest implements Extension {
	
	<T> void processAnnotatedType(@Observes @WithAnnotations(TestBinding.class) ProcessAnnotatedType<T> pat) {
        System.out.println("--- extensions.SimpleTestProcessAnnotatedType --- Scanning type: " + pat.getAnnotatedType().getJavaClass().getName());
    }

}
