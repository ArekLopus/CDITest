package helper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestBeanApplicationScoped {
	
	public String callMe() {
		System.out.println("-------- TestBeanRequestScoped.callMe()");
		return "TestBeanApplicationScoped.callMe() called";
	}
	
	@PostConstruct
	private void init() {
		System.out.println("TestBeanApplicationScoped's @PostConstruct");
	}
	
	@PreDestroy
	private void cleanup() {
		System.out.println("TestBeanApplicationScoped's @PreDestroy");
	}

}
