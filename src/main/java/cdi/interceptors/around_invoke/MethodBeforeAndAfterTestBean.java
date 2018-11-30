package cdi.interceptors.around_invoke;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MethodBeforeAndAfterTestBean {
	
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
	
	public void callMe() {
		System.out.println("callMe() called");
		throw new IllegalStateException("Illegal State Exception was thrown.");
	}

}
