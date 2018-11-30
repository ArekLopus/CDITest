package jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.interceptors.InterceptorsTestInterceptor;

@Interceptors(InterceptorsTestInterceptor.class)
@Path("interceptors")
@RequestScoped
public class InterceptorsResource {
	
	@GET
	public String interceptorsTest() {
		System.out.println("BindingInterceptorsResource's interceptorsTest() called.");
		return "InterceptorsResource ";
	}

}
