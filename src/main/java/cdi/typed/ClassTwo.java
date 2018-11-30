package cdi.typed;

import javax.enterprise.inject.Typed;

@Typed(ClassTwo.class)
public class ClassTwo extends ClassOne {

	public String classTest() {
		return "ClassTwo's method called";
	}

}
