package utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.Context;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

public class FindBeanInAScope { 

	public static List<Object> getAllScopedBeans(Class<? extends Annotation> scope, Annotation... qualifiers) {
		
		BeanManager bm = CDI.current().getBeanManager();
		List<Object> allBeans = new ArrayList<>();
	    Set<Bean<?>> beans = bm.getBeans(Object.class, qualifiers);
	    
	    // Context has to be active!
	    Context context = bm.getContext(scope);

	    for (Bean<?> bean : beans) {
	        Object instance = context.get(bean);
	        if (instance != null) {
	            allBeans.add(instance);
	        }
	    }

	    return allBeans;
	}
}
