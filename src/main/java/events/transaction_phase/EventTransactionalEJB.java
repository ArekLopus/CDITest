package events.transaction_phase;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import qualifiers.QualifierString;

@Stateless
public class EventTransactionalEJB {
	
	@Resource
	EJBContext ctx;
	
	@Inject
	@QualifierString("tr")
	private Event<String> event;
	
	@Inject
	@QualifierString("tr2")
	private Event<String> event2;
	
	
	public void transTestSuccess() {
		event.fire("Transactional test");
	}
	
	public void transTestFail() {
		ctx.setRollbackOnly();
		event.fire("Transactional test");
	}
	
	
	public void transTestSuccess2() {
		event2.fire("Transactional test 2");
	}
	
	public void transTestFail2() {
		ctx.setRollbackOnly();
		event2.fire("Transactional test 2");
	}
}
