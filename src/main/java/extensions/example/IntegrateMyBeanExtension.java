package extensions.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.util.AnnotationLiteral;

//https://www.theserverside.com/tip/Dependency-Injection-in-Java-EE-6-Part-6
//-Because we are adding beans, it makes the most sense to observe the AfterBeanDiscovery event, which is what we are doing
// in the onBeanDiscovery(), which also gets a BeanManager instance.
//-The first thing we do is create a convenience InjectionTarget instance for our custom bean. This is useful because we want CDI
// to inject its available beans into our custom object and also as a convenience for impl the Bean interface’s getInjectionPoints().
//-We then register our custom bean with CDI using the event’s addBean(). The rest of the code is pretty unremarkable except
// for the create and destroy methods that are used to create and destroy the custom bean instances.
//-In the create(), the injectionTarget.produce call is used to have CDI create the actual custom bean instance so that
// any constructor injections can take place or in case a producer is used.
//-The injectionTarget.inject call then performs any other injection on the newly created custom object instance.
//-Conversely, in the destroy method, the injectionTarget.dispose call is used so that CDI can call a disposer()
// if it needs to while the context.release() call makes sure any dependent objects are properly cleaned up.

public class IntegrateMyBeanExtension implements Extension {

	
	public void onBeanDiscovery(@Observes AfterBeanDiscovery beanDiscovery, BeanManager beanManager) {

		
		// Create a convenience injection target since we will inject into our custom bean
		InjectionTarget<IntegrateMyBean> injectionTarget = beanManager.createInjectionTarget(beanManager.createAnnotatedType(IntegrateMyBean.class));

		
		// Register the custom bean
		beanDiscovery.addBean(new Bean<IntegrateMyBean>() {

			@Override
			public Class<?> getBeanClass() {
				return IntegrateMyBean.class;
			}

			@Override
			public Set<InjectionPoint> getInjectionPoints() {
				return injectionTarget.getInjectionPoints();
			}

			@Override
			public String getName() {
				return "myBean";
			}

			@SuppressWarnings("serial")
			@Override
			public Set<Annotation> getQualifiers() {
				Set<Annotation> qualifiers = new HashSet<Annotation>();
				qualifiers.add(new AnnotationLiteral<Default>() {});
				qualifiers.add(new AnnotationLiteral<Any>() {});
				return qualifiers;
			}

			@Override
			public Class<? extends Annotation> getScope() {
				return ApplicationScoped.class;
			}

			@Override
			public Set<Class<? extends Annotation>> getStereotypes() {
				return Collections.emptySet();
			}

			@Override
			public Set<Type> getTypes() {
				Set<Type> types = new HashSet<Type>();
				types.add(IntegrateMyBean.class);
				return types;
			}

			@Override
			public boolean isAlternative() {
				return false;
			}

			@Override
			public boolean isNullable() {
				return false;
			}

			@Override
			public IntegrateMyBean create(CreationalContext<IntegrateMyBean> context) {
				
				// Have CDI instantiate the bean
				IntegrateMyBean instance = (IntegrateMyBean) injectionTarget.produce(context);

				// Have CDI perform injection
				injectionTarget.inject(instance, context);

				// Call @PostConstruct
				injectionTarget.postConstruct(instance);

				return instance;
			}

			@Override
			public void destroy(IntegrateMyBean instance, CreationalContext<IntegrateMyBean> context) {
				// Call @PreDestroy
				injectionTarget.preDestroy(instance);

				// Have CDI release the bean
				injectionTarget.dispose(instance);

				// Release any dependent objects
				context.release();
				
			}
		});
	}
}