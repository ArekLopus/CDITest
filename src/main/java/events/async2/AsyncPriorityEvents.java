package events.async2;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import qualifiers.QualifierInt;

//-Observers with the same priority are invoked in an unpredictable order
//-The default order is javax.interceptor.Interceptor.Priority.APPLICATION + 500.
//-@Priority specifies the order in which observers should be called, smaller numbers first.

//http://localhost:8080/CDITest/res/priorityEvent
@Path("priorityEvent")
@ApplicationScoped
public class AsyncPriorityEvents {
	
	@Inject
	@QualifierInt(82)
	Event<String> evt;
	
	AsyncResponse ar;
	private String resp ="";
	
	
	public void asyncObsrvr1(@Priority(10) @QualifierInt(82) @ObservesAsync String str) {
		resp = "Observed String @Priority(10): " + str + ", Thread -> "+Thread.currentThread().getName();
		System.out.println("AsyncPriorityEvent @Priority(10) - Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
	}
	public void asyncObsrvr2(@Priority(99) @QualifierInt(82) @ObservesAsync String str) {
		resp += "<br/>Observed String @Priority(99): " + str + ", Thread -> "+Thread.currentThread().getName();
		ar.resume(resp);
		System.out.println("AsyncPriorityEvent @Priority(99) - Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
	}
	
	
	@GET
	public void testAyncEvent(@Suspended AsyncResponse ar) {
		this.ar = ar; 
		asyncEvents("This is String fired by async Event!");
		
	}
	
	public void asyncEvents(String str) {
		evt.fireAsync(str);
	}
}
