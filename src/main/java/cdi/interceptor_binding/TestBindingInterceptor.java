package cdi.interceptor_binding;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Priority;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.Intercepted;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TestBinding
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TestBindingInterceptor {
	
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		System.out.println("TestBindingInterceptor's @AroundInvoke, method -> " + ctx.getMethod().getName());
		return ctx.proceed();
	}
	
	@AroundConstruct
	private Object testAroundConstruct(InvocationContext ctx) throws Exception {
		System.out.println("TestBindingInterceptor's @AroundConstruct");
		return ctx.proceed();
	}
	
	@PostConstruct
	private Object init(InvocationContext ctx) throws Exception {
		System.out.println("TestBindingInterceptor's @PostConstruct");
		return ctx.proceed();
	}
	@PreDestroy
	private Object destroy(InvocationContext ctx) throws Exception {
		System.out.println("TestBindingInterceptor's @PreDestroy");
		iterceptedTest();
		return ctx.proceed();
	}
	
	
	@Inject
    @Intercepted
    private Bean<?> bean;
	
	@Inject
	BeanManager beanManager;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void iterceptedTest() {
		
		System.out.println("----------- @Intercepted test ---------");
    	System.out.println("bean: " + bean);
		System.out.println("bean.getName(): " + bean.getName());
		System.out.println("bean.isAlternative(): " + bean.isAlternative());
		System.out.println("bean.isNullable(): " + bean.isNullable());
		System.out.println("bean.getBeanClass(): " + bean.getBeanClass());
		System.out.println("bean.getInjectionPoints(): " + bean.getInjectionPoints());
		System.out.println("bean.getQualifiers(): " + bean.getQualifiers());
		System.out.println("bean.getScope(): " + bean.getScope());
		System.out.println("bean.getStereotypes(): " + bean.getStereotypes());
		
		//public interface Bean<T> extends Contextual<T>, BeanAttributes<T>
		Context context = beanManager.getContext(bean.getScope());
		Object object = context.get((Contextual)bean, beanManager.createCreationalContext(bean));
		System.out.println("object instanceof TestBindingBean -> " + (object instanceof TestBindingBean));
	}

}
