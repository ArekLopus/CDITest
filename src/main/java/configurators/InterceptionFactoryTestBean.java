package configurators;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InterceptionFactory;
import javax.enterprise.inject.spi.configurator.AnnotatedConstructorConfigurator;
import javax.enterprise.inject.spi.configurator.AnnotatedFieldConfigurator;
import javax.enterprise.inject.spi.configurator.AnnotatedMethodConfigurator;

import cdi.interceptor_binding.TestBinding;
import qualifiers.QualifierString;

public class InterceptionFactoryTestBean {
	
	@Produces
	@QualifierString("InterceptionFactory")
	public TesterBean producerMethodForTestingInterceptionFactory(InterceptionFactory<TesterBean> factory) {
		
		factory.configure().add(TestBinding.Literal.INSTANCE);
		
		Set<AnnotatedConstructorConfigurator<TesterBean>> constructors = factory.configure().constructors();
		Set<AnnotatedFieldConfigurator<? super TesterBean>> fields = factory.configure().fields();
		List<AnnotatedMethodConfigurator<? super TesterBean>> methodsFiltered = factory.configure().filterMethods(m -> m.getJavaMember().getName().contains("first")).collect(Collectors.toList());   
		Set<AnnotatedMethodConfigurator<? super TesterBean>> methods = factory.configure().methods();
		//AnnotatedTypeConfigurator<TesterBean> removeAll = factory.configure().removeAll();
		
		System.out.println("Constructors: " + constructors.size());
		System.out.println("Fields: " + fields.size());
		System.out.println("Mmethods Filtered: " + methodsFiltered.size());
		System.out.println("Methods: " + methods.size());
		
        return factory.createInterceptedInstance(new TesterBean());
	}
	
}
