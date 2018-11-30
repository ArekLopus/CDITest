package generics;

import javax.enterprise.inject.Produces;

import helper.TestBeanParameterized;
import qualifiers.QualifierWithNoMemebers;

public class ParameterizedProducer  {
	
	@Produces
	public TestBeanParameterized<String> name() {
		return new TestBeanParameterized<String>("String Generic");
	}
	
	@Produces
	@QualifierWithNoMemebers
	public TestBeanParameterized<String> name1() {
		return new TestBeanParameterized<String>("String Generic with Qualifier");
	}
	
	@Produces
	public TestBeanParameterized<StringBuilder> name2() {
		return new TestBeanParameterized<StringBuilder>(new StringBuilder("StringBuilder Generic"));
	}
	
	@Produces
	public TestBeanParameterized<StringBuffer> name3() {
		return new TestBeanParameterized<StringBuffer>(new StringBuffer("StringBuffer Generic"));
	}
}
