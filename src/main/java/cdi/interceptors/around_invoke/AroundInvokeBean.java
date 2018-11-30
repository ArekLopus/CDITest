package cdi.interceptors.around_invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

//-Use the @AroundInvoke annotation to designate interceptor methods for managed object methods.
//-Only one around-invoke interceptor method per class is allowed.
//-Around-invoke interceptor methods have the following form:
//	@AroundInvoke
//	visibility Object method-name(InvocationContext) throws Exception { ... }
//-Around-invoke interceptor methods can have public, private, protected, or package-level access, and must not be declared static or final.
//-An around-invoke interceptor can call any component or resource that is callable by the target method on which it interposes,
// can have the same security and transaction context as the target method, and can run in the same JVM call stack as the target method.
//-Around-invoke interceptors can throw runtime exceptions and any exception allowed by the throws clause of the target method.
// They may catch and suppress exceptions, and then recover by calling the InvocationContext.proceed method.

//- Must be @Dependant if declared in a separate @Interceptor class
//-org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-001476: 
//Interceptor [class cdi.interceptors.around_invoke.AroundInvokeInterceptor intercepts ] must be @Dependent

//	AroundInvokeTest's @PostConstruct - @AroundInvoke called - aroundInvokeTest() called - AroundInvokeTest's @PreDestroy
@RequestScoped
public class AroundInvokeBean {
	
	public String aroundInvokeTest(String first, String second) {
		System.out.println("aroundInvokeTest() called");
		return "aroundInvokeTest() called";
	}
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		System.out.println("----- @AroundInvoke interceptor method called -----");
		
		Constructor<?> constructor = ctx.getConstructor();
		Map<String, Object> contextData = ctx.getContextData();
		Method method = ctx.getMethod();
		Object[] parameters = ctx.getParameters();
		Object target = ctx.getTarget();
		
		System.out.println("Constructor: " + constructor);
		System.out.println("ContextData :" + contextData);
		System.out.println("Method :" + method);
		System.out.println("Parameters :" + Arrays.asList(parameters));
		System.out.println("target instanceof AroundInvokeTest: " + (target instanceof AroundInvokeBean));
		
		return ctx.proceed();
	}
	
	@PostConstruct
	private void init() {
		System.out.println("AroundInvokeBean's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundInvokeBean's @PreDestroy");
	}
}
