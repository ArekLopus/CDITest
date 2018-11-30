package jaxrs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.interceptors.around_construct.AroundConstructInterceptor;

@Interceptors(AroundConstructInterceptor.class)
@RequestScoped
@Path("aroundconstruct")
public class AroundConstructResource {
	
	@GET
	public String specializedTest() {
		return "AroundConstructTest";
	}
	
	
	public AroundConstructResource() {
		System.out.println("AroundConstructResource() constructor called");
	}
	
	
	@PostConstruct
	private void init() {
		System.out.println("AroundConstructResource's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundConstructResource's @PreDestroy");
	}
}
