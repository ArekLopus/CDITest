package events.async.v2_0;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

import qualifiers.QualifierInt;

@ApplicationScoped
public class AsyncObservers {
	
	public void asyncObsrvr1(@Priority(10) @ObservesAsync @QualifierInt(95) String str) {
		System.out.println("Observed String: " + str + ", Thread -> " + Thread.currentThread().getName());
	}
	
	public void asyncObsrvr2(@Priority(99) @Observes @QualifierInt(95) String str) {
		System.out.println("Observed String @Priority(99): " + str + ", Thread -> " + Thread.currentThread().getName());
	}
	
}
