package generics;

import javax.enterprise.event.Observes;

import helper.TestBeanParameterized;
import qualifiers.QualifierWithNoMemebers;

public class ParameterizedEventObserver  {
	
	
	public void parameterizedBean1(@Observes TestBeanParameterized<String> str) {
		System.out.println("parameterizedBean1(@Observes TestBeanParameterized<String>) " + str);
	}

	public void parameterizedBean2(@Observes @QualifierWithNoMemebers TestBeanParameterized<String> str) {
		System.out.println("parameterizedBean2(@Observes @QualifierWithNoMemebers TestBeanParameterized<String>) " + str);
	}
	
	public void parameterizedBean3(@Observes TestBeanParameterized<StringBuilder> str) {
		System.out.println("parameterizedBean3(@Observes TestBeanParameterized<String>) " + str);
	}
	
	public void parameterizedBean4(@Observes TestBeanParameterized<Integer> str) {
		System.out.println("parameterizedBean4(@Observes TestBeanParameterized<String>) " + str);
	}
}
