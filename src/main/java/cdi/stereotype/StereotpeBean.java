package cdi.stereotype;

@MyStereotype
public class StereotpeBean {
	
	public void testMethod() {
		System.out.println("AroundConstructBean's testMethod() called");
	}
	
}
