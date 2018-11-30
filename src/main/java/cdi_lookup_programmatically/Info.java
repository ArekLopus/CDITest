package cdi_lookup_programmatically;

//-In certain situations, injection is not the most convenient way to obtain a contextual reference. Fe, it may not be used when:
//  • the concrete type of the objects to be injected may vary at runtime.
//  • we would like to iterate over all beans of a certain type.
//	• depending upon the deployment, there may be no bean which satisfies the type and qualifiers.
//  • the objects require some custom initialization that is not performed by the bean constructor.
//  • the objects to be injected are not required to be instances of beans.

//-Programmatic lookup allows to resolve a bean at runtime or find all beans that match a given type thanks to the Instance intf.
// 1. Instance injection points are always satisfied and never fail at deployment time
// 2. Instance provides test methods to know if requesting an instance is safe
// 3. With Instance you control when a bean instance is requested with the get() method


//-To select a bean programmatically (as opposite of declaratively by annotations).
//		Instance<String> str;
//		str = CDI.current().select();

//-To select a specific instance (@Inject injects all beans that matches).
//		@Inject
//		Instance<String> str
//		str.select()

//-To pick up observer for Event
//		@Inject
//		Event<String> event;
//		event.select().fire()

//@Inject @Blog Event<Document> blogEvent;
//blogEvent.select(new AnnotationLiteral<Updated>(){}).fire(document);
//-The above shown event is fired with two qualifiers - @Blog and @Updated. 


//-select() methods
//	select(Annotation… qualifiers)
//	select(Class<U> subtype, Annotation… qualifiers)
//	select(TypeLiteral<U> subtype, Annotation… qualifiers)


public class Info {}
