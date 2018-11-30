package events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.EventMetadata;
import javax.enterprise.inject.spi.InjectionPoint;

import cdi.alternative.WorkerIntf;
import qualifiers.QualifierInt;
import qualifiers.QualifierString;
import qualifiers.QualifierWithNoMemebers;

@ApplicationScoped
public class Observers {
	
	//-If no @QualifierString, it caches all Strings, even if injection point is with qualifier
	//	( @Inject @QualifierString("One") Event<String> event; )
	//-Because of: An observer method is notified if the set of observer qualifiers is a subset of the fired event's qualifiers
	public void simpleObserver(@Observes @QualifierString String str) {
		System.out.println("Simple Observer, no qualifiers, message: " + str);
	}

	public void qualifierOne(@Observes @QualifierString("One") String str) {
		System.out.println("Qualifier Observer, @QualifierString('One'), message: " + str);
	}
	public void qualifierTwo(@Observes @QualifierString("Two") String str) {
		System.out.println("Qualifier Observer, @QualifierString('Two'), message: " + str);
	}
	
	
	public void qualifier1(@Observes @QualifierInt(1) String str) {
		System.out.println("Qualifier Observer, @QualifierInt(1), message: " + str);
	}
	public void qualifier2(@Observes @QualifierInt(2) String str) {
		System.out.println("Qualifier Observer, @QualifierInt(2), message: " + str);
	}
	
	
	public void autoInjectedParameter(@Observes @QualifierString("Testing Auto Injected Method Params") String str, WorkerIntf wi) {		//WorkerIntf is auto injected
		System.out.println(str);
		System.out.println(wi.work());
	}
	
	public void qualifier3(@Observes @QualifierWithNoMemebers String str) {
		System.out.println("Qualifier Observer, @QualifierTest, message: " + str);
	}
	
	
	public void observerWithMetadata(@Observes @QualifierString("Metadata") String str, EventMetadata meta) {
		System.out.println("Observer With Metadata, @QualifierString('Metadata'), message: " + str);
		
		InjectionPoint injectionPoint = meta.getInjectionPoint();
		Set<Annotation> qualifiers = meta.getQualifiers();
		Type type = meta.getType();
		
		System.out.println("InjectionPoints: " + injectionPoint);
		System.out.println("Qualifiers: " + qualifiers);
		System.out.println("Type: " + type);
	}
	
	
	public void observerSB(@Observes StringBuilder str) {
		System.out.println("@Observes StringBuilder, message: " + str);
	}
}
