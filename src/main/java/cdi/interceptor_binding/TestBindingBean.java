package cdi.interceptor_binding;

import javax.enterprise.context.RequestScoped;

@TestBinding
@RequestScoped
public class TestBindingBean {

	public TestBindingBean() {
		System.out.println(TestBindingBean.class.getSimpleName() + " - constructor called.");
	}
	
	public String methodCallTest() {
		String info = TestBindingBean.class.getSimpleName() + " - methodCallTest() called.";
		System.out.println(info);
		return info;
	}
}
