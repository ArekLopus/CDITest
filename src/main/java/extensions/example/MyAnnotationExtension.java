package extensions.example;

import java.lang.reflect.Field;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.enterprise.inject.spi.WithAnnotations;

import extensions.example.MyAnnotation.UserType;

public class MyAnnotationExtension implements Extension {
	
	<T> void processAnnotatedType2(@Observes @WithAnnotations(MyAnnotation.class) ProcessAnnotatedType<T> pat, BeanManager bm) {
        
		AnnotatedType<T> at = pat.getAnnotatedType();
		Class<?> clazzLocal = at.getJavaClass();
		//System.out.println("--------- cl "+ clazzLocal);
		
        MyAnnotation annotation = at.getAnnotation(MyAnnotation.class);
        UserType user =  annotation.value();
        
        //Cant set non-static field here - instance doesnt exist.
        try {
			Field field = clazzLocal.getField("infoStatic");
			field.set(null, "infoStatic Set In MyAnnotationExtension, MyAnnotation user: " + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	

	// Used at ACTUAL injection time!!! Possible to change injected OBJECT here
	public <X> void processInjectionTarget(@Observes ProcessInjectionTarget<X> pit) {
		
		InjectionTarget<X> it = pit.getInjectionTarget();
	    AnnotatedType<X> at = pit.getAnnotatedType();
	    
	    // We wrap all available Injection Targets in a custom wrapper that will add custom behavior to inject()
	    InjectionTarget<X> wrapper = new InjectionTarget<X>() {
	    	
	    	private Class<?> clazz;
	    	
	    	// The container calls inject() method when performing field injection and calling bean initializer methods.
	    	@Override
	    	public void inject(X instance, CreationalContext<X> ctx) {
	    		it.inject(instance, ctx);
		        
		        if(instance.getClass().isAnnotationPresent(MyAnnotation.class)) {
//			    if(at.isAnnotationPresent(MyAnnotation.class)) {
			    	clazz = at.getJavaClass();
			    }
		        
		        if(instance.getClass().equals(clazz)) {
		        	
		        	MyAnnotation annotation = at.getAnnotation(MyAnnotation.class);
		            UserType user =  annotation.value();
		        	
		        	System.out.println("--------- Inside InjectionTarget" + instance.getClass().getName());
		        	try {
						Field field = at.getJavaClass().getField("info");
						if(field != null) {
							field.setAccessible(true);
							field.set(instance, "info Set In MyAnnotationExtension, MyAnnotation user: " + user);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
	    	}
	    	
	    	@Override
	    	public X produce(CreationalContext<X> ctx) {
	    		return it.produce(ctx);
	    	}

	    	@Override
	    	public void dispose(X instance) {
	    		it.dispose(instance);
	    	}

	    	@Override
	    	public Set<InjectionPoint> getInjectionPoints() {
	    		return it.getInjectionPoints();
	    	}

	    	@Override
	    	public void postConstruct(X instance) {
	    		it.postConstruct(instance);
	    	}

	    	@Override
	    	public void preDestroy(X instance) {
	    		it.preDestroy(instance);
	    	}

	    };

	    pit.setInjectionTarget(wrapper);
	}
}
