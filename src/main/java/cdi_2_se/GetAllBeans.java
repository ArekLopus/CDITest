package cdi_2_se;

import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

public class GetAllBeans {
	
	public static void main(String... args) {
		
		try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			
			BeanManager beanManager = container.getBeanManager();
			
			//Set<Bean<?>> allBeans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {});
			Set<Bean<?>> allBeans = beanManager.getBeans(Object.class, Any.Literal.INSTANCE);
			
			System.out.println("All beans size: " + allBeans.size());
			
			//Only the first bean
			for(Bean<?> bean: allBeans) {
				System.out.println(bean);
				break;
			}
		}
	}
}