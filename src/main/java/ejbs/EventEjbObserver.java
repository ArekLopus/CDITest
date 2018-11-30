package ejbs;

import javax.enterprise.event.Observes;

import helper.TestBeanEJB;

public class EventEjbObserver {

	public void observeEJB(@Observes TestBeanEJB ejb) {
		System.out.println("@Observes TestBeanEJB ejb -> " + ejb.callMe());
	}
	
}
