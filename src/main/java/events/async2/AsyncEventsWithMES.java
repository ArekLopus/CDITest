package events.async2;

import java.util.concurrent.CompletionStage;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import qualifiers.QualifierInt;

//http://localhost:8080/CDITest/res/asyncEvent2
@Path("asyncEvent2")
@ApplicationScoped
public class AsyncEventsWithMES {
	
	@Resource
    ManagedExecutorService threadPool;
	
	@Inject
	@QualifierInt(81)
	Event<String> evt;
	
	AsyncResponse ar;
	
	
	public void asyncEvents(String str) {
		CompletionStage<String> fa = evt.fireAsync(str, NotificationOptions.ofExecutor(threadPool));
		fa.thenAccept(this::eventDelivered);
	}
	void eventDelivered(String event) {
        System.out.println("Event Delivered to thenAccept() : "+ event + ", Thread -> "+Thread.currentThread().getName());
    }
	
	//Observed String: This is String fired by async Event!, Thread -> concurrent/__defaultManagedExecutorService-managedThreadFactory-Thread-1
	public void asyncObsrvr(@ObservesAsync @QualifierInt(81) String str) {
		ar.resume("Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
		System.out.println("AsyncEventsWithMES - Observed String: " + str + ", Thread -> "+Thread.currentThread().getName());
	}
	
	
	@GET
	public void testAyncEvent(@Suspended AsyncResponse ar) {
		this.ar = ar; 
		asyncEvents("This is String fired by async Event!");
		
	}
}
