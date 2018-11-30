package events.conditional;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import qualifiers.QualifierString;

//-By default, if there is no instance of an observer in the current context,
// the container will instantiate the observer in order to deliver an event.
// It isnt always desirable. We may want to deliver events only to instances
// of the observer that already exist in the current contexts.

//-A conditional observer is specified by adding notifyObserver=Reception.IF_EXISTS to the @Observes annotation.
//	public void refreshOnDocUpdate(@Observes(notifyObserver=Reception.IF_EXISTS) @Updated Document d) { ... }

//Note: A bean with scope @Dependent cannot be a conditional observer, since it would never be called!


@RequestScoped
public class EventConditionalObservers {

	public void cndtnl1(@Priority(1) @Observes(notifyObserver=Reception.IF_EXISTS) @QualifierString("Conditional") String str) {
		System.out.println("Request scoped conditional: " + str);
	}

	public void cndtnl2(@Priority(5) @Observes(notifyObserver=Reception.ALWAYS) @QualifierString("Conditional") String str) {
		System.out.println("Request scoped: " + str);
	}
	
}
