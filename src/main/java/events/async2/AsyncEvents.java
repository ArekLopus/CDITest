package events.async2;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import qualifiers.QualifierInt;

//http://localhost:8080/CDITest/res/asyncEvent
@Path("asyncEvent")
@ApplicationScoped
public class AsyncEvents {
	
	@Inject
	@QualifierInt(80)
	Event<String> evt;
	
	AsyncResponse ar;
	
	
	public void asyncEvents(String str) {
		evt.fireAsync(str);
	}
	
	//Observed String: This is String fired by async Event!, Thread -> weld-worker-1]]
	public void asyncObsrvr(@QualifierInt(80) @ObservesAsync String str) {
		ar.resume("Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
		System.out.println("Async Event - Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
	}
	
	
	@GET
	public void testAyncEvent(@Suspended AsyncResponse ar) {
		this.ar = ar; 
		asyncEvents("This is String fired by async Event!");
		
	}
}
