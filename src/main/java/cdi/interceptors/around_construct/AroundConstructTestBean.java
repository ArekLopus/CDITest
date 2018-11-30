package cdi.interceptors.around_construct;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;

@Interceptors(AroundConstructInterceptor.class)
@RequestScoped
public class AroundConstructTestBean {
	
	public AroundConstructTestBean() {
		System.out.println("AroundConstructBean - constructor called");
	}
	
	public void testMethod() {
		System.out.println("AroundConstructBean's testMethod() called");
	}
	
	@PostConstruct
	private void init() {
		System.out.println("AroundConstructBean's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundConstructBean's @PreDestroy");
	}
}
