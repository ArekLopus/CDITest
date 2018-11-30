package cdi_2_se;

import javax.enterprise.context.control.RequestContextController;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import helper.TestBeanRequestScoped;

public class ManagedBean {
	
	public static void main(String... args) {
		
		try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			
			RequestContextController rcc = container.select(RequestContextController.class).get();
			
			rcc.activate();
			TestBeanRequestScoped bean = container.select(TestBeanRequestScoped.class).get();
			
			String info = bean.callMe();
			
			//Not needed, it is destroyed with this request scoped bean. 
			container.destroy(bean);
			rcc.deactivate();
			
			System.out.println(info);
		}
	}
}