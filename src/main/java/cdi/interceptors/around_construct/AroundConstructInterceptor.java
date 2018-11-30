package cdi.interceptors.around_construct;

import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

//-Used to intercept the construction of the target class, invoke logic prior to the constructor of the target class is invoked.

//-An AroundConstruct interceptor method may be ONLY declared on an interceptor class.

//-Designates an interceptor method that receives a callback when the target class constructor is invoked.
//-An interceptor class must not declare more than one AroundConstruct method.
//-AroundConstruct methods may throw runtime exceptions, but not checked exceptions.
//-The target instance is created and its constructor injection is performed, if applicable, when the last interceptor method
// in the AroundConstruct interceptor chain invokes the InvocationContext.proceed() method.
//-An AroundConstruct interceptor method should exercise caution accessing the instance which constructor it interposes on.

//-The @AroundConstruct method is called after DI has been completed for all interceptors associated with the target class.
//-The target class is created and the target class’s constructor injection is performed after all associated @AroundConstruct methods
// have called the Invocation.proceed(). At that point, DI for the target class is completed, and then any @PostConstruct callback methods are invoked.
//-@AroundConstruct methods can access the constructed target instance after calling Invocation.proceed by calling the InvocationContext.getTarget().
@Interceptor
public class AroundConstructInterceptor {
	
	@AroundConstruct
	private Object testAroundConstruct(InvocationContext ctx) {
		System.out.println("@AroundConstruct called");
		
		try {
			return ctx.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
//-WELD-001471: Interceptor method testAroundConstruct defined on class cdi.interceptors.AroundConstructTest is not defined
// according to the specification. It should not throw java.lang.Exception, which is a checked exception.
// at cdi.interceptors.AroundConstructTest.testAroundConstruct(AroundConstructTest.java:21)