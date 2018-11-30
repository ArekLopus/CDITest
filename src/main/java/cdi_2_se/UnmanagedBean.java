package cdi_2_se;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.Unmanaged;
import javax.enterprise.inject.spi.Unmanaged.UnmanagedInstance;

import helper.TestBeanRequestScoped;

public class UnmanagedBean {
	
	public static void main(String... args) {
		
		try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			
			Unmanaged<TestBeanRequestScoped> unmanagedBean = new Unmanaged<>(TestBeanRequestScoped.class);
			UnmanagedInstance<TestBeanRequestScoped> beanInstance = unmanagedBean.newInstance();
			TestBeanRequestScoped bean = beanInstance.produce().inject().postConstruct().get();
			
			String info = bean.callMe();
			
			beanInstance.preDestroy().dispose();
					
			System.out.println(info);
		}
	}
}