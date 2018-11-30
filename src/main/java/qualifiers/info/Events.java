package qualifiers.info;

import javax.enterprise.event.Observes;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;

public class Events {
	
	public void simpleObserver1(@Observes StringBuilder str) {
		System.out.println("StringBuilder Observer, 0 qualifiers, message: " + str.toString());
	}
	
	public void simpleObserver2(@Observes @QualifierString StringBuilder str) {
		System.out.println("StringBuilder Observer, 1 qualifier, message: " + str.toString());
	}

	public void simpleObserver3(@Observes @QualifierString @QualifierInt StringBuilder str) {
		System.out.println("StringBuilder Observer, 2 qualifiers, message: " + str.toString());
	}
	
}
