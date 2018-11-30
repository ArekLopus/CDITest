package cdi.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Decorated;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;

@Decorator
public class TalkerDecorated implements TalkerIntf {
	
	@Inject
	@Delegate
	TalkerIntf tk;
	
	@Inject
    @Decorated
    Bean<TalkerIntf> bean;
	
	@Override
	public String greet() {
		System.out.println("bean: " + bean);
		System.out.println("bean.getName(): " + bean.getName());
		System.out.println("bean.isAlternative(): " + bean.isAlternative());
		System.out.println("bean.isNullable(): " + bean.isNullable());
		System.out.println("bean.getBeanClass(): " + bean.getBeanClass());
		System.out.println("bean.getInjectionPoints(): " + bean.getInjectionPoints());
		System.out.println("bean.getQualifiers(): " + bean.getQualifiers());
		System.out.println("bean.getScope(): " + bean.getScope());
		System.out.println("bean.getStereotypes(): " + bean.getStereotypes());
	
		return "TalkerDecorated.class, The original message was -> " + tk.greet();
	}

}
