package cdi.interceptors;

import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;

@Interceptors(InterceptorsTestInterceptor.class)
@RequestScoped
public class InterceptorsTestBean {
	
	public InterceptorsTestBean() {
		System.out.println("InterceptorsTestBean - constructor called, object " + this);
	}
	
	public void testMethod() {
		System.out.println("InterceptorsTestBean's testMethod() called");
	}

}
