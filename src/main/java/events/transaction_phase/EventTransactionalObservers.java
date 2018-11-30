package events.transaction_phase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

import qualifiers.QualifierString;

@ApplicationScoped
public class EventTransactionalObservers {
	
	public void transactional1(@Observes(during=TransactionPhase.AFTER_SUCCESS) @QualifierString("tr")  String str) {
		System.out.println("TransactionPhase.AFTER_SUCCESS: " + str);
	}

	public void transactional2(@Observes(during=TransactionPhase.AFTER_FAILURE) @QualifierString("tr") String str) {
		System.out.println("TransactionPhase.AFTER_FAILURE: " + str);
	}

	
	
	// Called when the event is fired
	public void transactional21(@Observes(during=TransactionPhase.IN_PROGRESS) @QualifierString("tr2") String str) {
		System.out.println("TransactionPhase.IN_PROGRESS: " + str);
	}
	public void transactional22(@Observes(during=TransactionPhase.BEFORE_COMPLETION) @QualifierString("tr2") String str) {
		System.out.println("TransactionPhase.BEFORE_COMPLETION: " + str);
	}
	public void transactional23(@Observes(during=TransactionPhase.AFTER_COMPLETION) @QualifierString("tr2") String str) {
		System.out.println("TransactionPhase.AFTER_COMPLETION: " + str);
	}
	public void transactional24(@Observes(during=TransactionPhase.AFTER_SUCCESS) @QualifierString("tr2") String str) {
		System.out.println("TransactionPhase.IAFTER_SUCCESS: " + str);
	}
	public void transactional25(@Observes(during=TransactionPhase.AFTER_FAILURE) @QualifierString("tr2") String str) {
		System.out.println("TransactionPhase.AFTER_FAILURE: " + str);
	}
	
}
