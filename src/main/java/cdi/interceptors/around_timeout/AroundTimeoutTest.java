package cdi.interceptors.around_timeout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

//@Singleton		//Commented out to turn it off
@SuppressWarnings("unused")
public class AroundTimeoutTest {
	
	int counter = 1;
	
	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void timerMethod() { 
		System.out.println("Scheduler... -> " + counter++);
	}

    @AroundTimeout
    public Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
    	System.out.println("AroundTimeout called");
    	
    	if(counter == 3) {
    		Object timer = ctx.getTimer();
        	if(timer != null) {
        		System.out.println("timer not null");
        		Timer tr = (Timer) timer;
        		System.out.println("Timer is cancelled after 3rd round.");
    			tr.cancel();	
        	}
		}
    	
    	return ctx.proceed();
    }
	
	
	@PostConstruct
	private void init() {
		System.out.println("AroundTimeoutTest's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundTimeoutTest's @PreDestroy");
	}	
}
