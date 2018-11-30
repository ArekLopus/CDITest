package cdi.interceptors.around_invoke;

import javax.enterprise.event.Observes;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import qualifiers.QualifierString;

@Interceptors(MethodBeforeAndAfterTestBean.class)
public class EventAroundInvokeTestBean {
	
	public String callMe(@Observes @QualifierString("aitest") String str) {
		System.out.println(EventAroundInvokeTestBean.class.getSimpleName() + "'s callMe() called in event observer");
		return "Event Around Invoke Test";
	}
	
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		
		System.out.println("local @AroundInvoke - before callMe() called");
		
		Object proceed = null;
		
		try {
			proceed = ctx.proceed();
		} catch (Exception e) {
			//throw e;			//do something with the exception
			System.out.println("Exception thrown: " + e.getMessage());
		} finally {
			System.out.println("local @AroundInvoke - after callMe() called");
			
		}
		
		return proceed;
	}
}
