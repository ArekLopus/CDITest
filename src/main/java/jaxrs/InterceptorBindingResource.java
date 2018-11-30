package jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.interceptor_binding.TestBindingBean;

@Path("bindinginterceptors")
@RequestScoped
public class InterceptorBindingResource {
	
	@Inject
	TestBindingBean tb;
	
	@GET
	public String interceptorsTest() {
		tb.methodCallTest();
		return "Interceptor Binding test. Check console for info";
	}

}
