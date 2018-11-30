package events;

import java.util.List;

import javax.enterprise.event.Observes;

import qualifiers.QualifierInt;

public class ObeserversParameterized {

	public void processNumberList(@Observes @QualifierInt(81) List<Number> list) {
		System.out.println("@Observes List<Number> Event: " + list);
	}
	
	public void processIntegerList(@Observes @QualifierInt(81) List<Integer> list) {
		System.out.println("@Observes List<Integer> Event: " + list);
	}
	
	
	public void processIntegerList2(@Observes @QualifierInt(86) List<? super Integer> list) {
		System.out.println("@Observes List<? super Integer> Event: " + list);
	}
	
	public void processNumberList2(@Observes @QualifierInt(86) List<? extends Number> list) {
		System.out.println("@Observes List<? extends Number> Event: " + list);
	}
	
}
