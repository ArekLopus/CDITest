package events.priority.v2_0;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import qualifiers.QualifierInt;

@ApplicationScoped
public class PriorityObservers {
	
	public void priority1(@Priority(10) @Observes @QualifierInt(99) String str) {
		System.out.println("Observed String @Priority(10): " + str);
	}
	public void priority2(@Priority(99) @Observes @QualifierInt(99) String str) {
		System.out.println("Observed String @Priority(99): " + str);
	}
	
}
