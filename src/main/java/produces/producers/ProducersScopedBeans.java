package produces.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import helper.JustABean;
import qualifiers.QualifierString;

public class ProducersScopedBeans {
	
	
	//FindBeanInAScopeServlet, ScopedBeansProcuced, BeanManagerTest
	@Produces
	@QualifierString("RequstScoped")
	@RequestScoped 
	public JustABean testABeanReq() {
		return new JustABean("RequstScoped JustABean");
	}
	
	
	@Produces
	@QualifierString("SessionScoped")
	@SessionScoped
	public JustABean testABeanSes() {
		return new JustABean("SessionScoped JustABean");
	}
	
	
	@Produces
	@QualifierString("ApplicationScoped")
	@ApplicationScoped
	public JustABean testABeanApp() {
		return new JustABean("ApplicationScoped JustABean");
	}
}
