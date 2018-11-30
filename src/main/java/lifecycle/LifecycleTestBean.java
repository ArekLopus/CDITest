package lifecycle;

import javax.enterprise.context.Dependent;
import javax.interceptor.Interceptors;

import cdi.interceptors.InterceptorsTestInterceptor2;

@Dependent
@Interceptors(InterceptorsTestInterceptor2.class)
public class LifecycleTestBean {

	public LifecycleTestBean() {
		System.out.println("LifecycleBean's constructor");
	}
	
	
	public void callMe() {
		System.out.println("LifecycleBean's callMe()");
	}
}
