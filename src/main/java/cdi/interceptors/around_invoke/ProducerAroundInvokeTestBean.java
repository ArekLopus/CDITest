package cdi.interceptors.around_invoke;

import javax.enterprise.inject.Produces;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import qualifiers.QualifierString;

@Interceptors(MethodBeforeAndAfterTestBean.class)
public class ProducerAroundInvokeTestBean {
	
	@Produces
	@QualifierString("aitest")
	public String callMe() {
		System.out.println(ProducerAroundInvokeTestBean.class.getSimpleName() + "'s callMe() called");
		return "Producer Around Invoke Test";
	}
	
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		
		System.out.println("before callMe() called");
		
		Object proceed = null;
		
		try {
			proceed = ctx.proceed();
		} catch (Exception e) {
			//throw e;			//do something with the exception
			System.out.println("Exception thrown: " + e.getMessage());
		} finally {
			System.out.println("after callMe() called");
			
		}
		
		return proceed;
	}
}
