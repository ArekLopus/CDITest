package cdi_lookup_programmatically.test;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestBeanApp {
	
	public String callMe() {
		return "TestBeanApp.callMe() called";
	}
}
