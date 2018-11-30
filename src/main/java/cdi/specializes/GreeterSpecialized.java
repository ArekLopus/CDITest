package cdi.specializes;

import javax.enterprise.inject.Specializes;

@Specializes
public class GreeterSpecialized extends Greeter {

	public String greet() {
		return "Hello from Specialized Greeter";
	}

}
