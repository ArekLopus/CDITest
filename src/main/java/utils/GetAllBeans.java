package utils;

import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

//There’s an easy way to find out what beans exist in the application:
public class GetAllBeans {
	
	public void getAllBeans() {
		
		BeanManager beanManager = CDI.current().getBeanManager();
		
		//Set<Bean<?>> allBeans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {});
		Set<Bean<?>> allBeans = beanManager.getBeans(Object.class, Any.Literal.INSTANCE);
		System.out.println("All beans size: " + allBeans.size());
		
	}
	
	

}
