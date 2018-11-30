package helper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class TestBeanEJB {
	
	public String callMe() {
		System.out.println("TestBeanEJB callMe() called");
		return "TestBeanEJB callMe() called";
	}
	
	@PostConstruct
	private void init() {
		System.out.println("TestBeanEJB's @PostConstruct");
	}
	
	@PreDestroy
	private void cleanup() {
		System.out.println("TestBeanEJB's @PreDestroy");
	}

}
