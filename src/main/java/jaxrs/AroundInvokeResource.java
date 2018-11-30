package jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.interceptors.around_invoke.AroundInvokeBean;

@Path("aroundinvoke")
public class AroundInvokeResource {
	
	@Inject
	AroundInvokeBean ai;
	
	@GET
	public String specializedTest() {
		return "AroundInvokeTest: " + ai.aroundInvokeTest("firstString", "secondString");
	}
	
}
