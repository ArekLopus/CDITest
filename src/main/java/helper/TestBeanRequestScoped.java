package helper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TestBeanRequestScoped {
	
	public String callMe() {
		System.out.println("-------- TestBeanRequestScoped.callMe()");
		return "TestBeanRequestScoped.callMe()";
	}
	
	@PostConstruct
	private void init() {
		System.out.println("TestBeanRequestScoped's @PostConstruct");
	}
	
	@PreDestroy
	private void cleanup() {
		System.out.println("TestBeanRequestScoped's @PreDestroy");
	}
	
}
