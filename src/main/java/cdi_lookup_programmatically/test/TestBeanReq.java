package cdi_lookup_programmatically.test;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TestBeanReq {
	
	public String callMe() {
		return "TestBeanReq.callMe()";
	}

}
