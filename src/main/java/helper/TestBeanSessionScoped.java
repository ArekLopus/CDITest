package helper;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class TestBeanSessionScoped implements Serializable {
	private static final long serialVersionUID = 1L;

	public String callMe() {
		System.out.println("-------- TestBeanSessionScoped.callMe()");
		return "TestBeanSessionScoped.callMe()";
	}
	
	@PostConstruct
	private void init() {
		System.out.println("TestBeanSessionScoped's @PostConstruct");
	}
	
	@PreDestroy
	private void cleanup() {
		System.out.println("TestBeanSessionScoped's @PreDestroy");
	}
	
}
