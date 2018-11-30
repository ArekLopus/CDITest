package cdi.interceptors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

//-Must be @Dependent
// org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-001476:
// Interceptor [class cdi.interceptors.InterceptorsTest intercepts ] must be @Dependent

@Interceptor
public class InterceptorsTestInterceptor {
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		System.out.println("InterceptorsTest's @AroundInvoke");
		return ctx.proceed();
	}
	
	@AroundConstruct
	private Object testAroundConstruct(InvocationContext ctx) throws Exception {
		System.out.println("InterceptorsTest's @AroundConstruct");
		return ctx.proceed();
	}
	
	@PostConstruct
	private Object init(InvocationContext ctx) throws Exception {
		System.out.println("InterceptorsTest's @PostConstruct");
		return ctx.proceed();
	}
	@PreDestroy
	private Object destroy(InvocationContext ctx) throws Exception {
		System.out.println("InterceptorsTest's @PreDestroy");
		return ctx.proceed();
	}
	
}
