package events;

//-Identifies the event parameter of an observer method. May be applied to a parameter of a method of a bean class or extension.
//		public void afterLogin(@Observes LoggedInEvent event) { ... }
	 
//-An observer method is a non-abstract method of a managed bean class or session bean class (or of an extension). 
//-An observer method may be either static or non-static.
//-If the bean is a session bean, the observer method must be either a business method of the EJB or a static method of the bean class.

//-Each observer method must have exactly one event parameter, of the same type as the event type it observes.
//-Event qualifiers may be declared by annotating the event parameter. When searching for observer methods for an event,
// the container considers the type and qualifiers of the event parameter.

//-If the event parameter does not explicitly declare any qualifier, the observer method observes events with no qualifier.

//-The event parameter type may contain a type variable or wildcard.

//-In addition to the event parameter, observer methods may declare additional parameters, which may declare qualifiers.
// These additional parameters are injection points.
//	 	public void afterLogin(@Observes LoggedInEvent event, @Manager User user, Logger log) { ... }

//-A bean (or extension) may declare multiple observer methods.

//-Observer methods are inherited by bean subclasses.

//-Interceptors and decorators may not declare observer methods (but can fire an event!).

public class AnInfo_Observer {}
