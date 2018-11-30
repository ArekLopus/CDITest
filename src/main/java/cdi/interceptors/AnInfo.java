package cdi.interceptors;

//-Although interceptors are part of Enterprise JavaBeans 3.2 and Contexts and Dependency Injection for Java EE 1.1
// You can use interceptors with session beans, message-driven beans, and CDI managed beans.
// In all of these cases, the interceptor target class is the bean class.
//-An interceptor can be defined within a target class as an interceptor method, or in an associated class called an interceptor class.
// Interceptor classes contain methods that are invoked in conjunction with the methods or lifecycle events of the target class.
//-Interceptor classes and methods are defined using metadata annotations, or in the DD of the app that contains the interceptors and target classes.
// Note: Applications that use the deployment descriptor to define interceptors are not portable across Java EE servers.

//=Interceptor methods within the target class or in an interceptor class are annotated with one of the metadata annotations:

// @AroundConstruct - Designates the method as an interceptor method that receives a callback after the target class is constructed
// @AroundInvoke - Designates the method as an interceptor method
// @AroundTimeout - Designates the method as a timeout interceptor for interposing on timeout methods for enterprise bean timers
// @PostConstruct - Designates the method as an interceptor method for post-construct lifecycle events
// @PreDestroy - Designates the method as an interceptor method for pre-destroy lifecycle events

//-Interceptor classes may be designated with the OPTIONAL @Interceptor, but interceptor classes are not required to be so annotated.
// An interceptor class must have a public, no-argument constructor.
//-The target class can have any number of interceptor classes associated with it.
// The order in which the interceptor classes are invoked is determined by the order in which the interceptor classes are defined in
// @Interceptors. However, this order can be overridden in the deployment descriptor.

//-Interceptor classes may be targets of dependency injection. Dependency injection occurs when the interceptor class instance
// is created, using the naming context of the associated target class, and before any @PostConstruct callbacks are invoked.

//-Lifecycle - Interceptor classes have the same lifecycle as their associated target class. When a target class instance is created,
// an interceptor class instance is also created for each declared interceptor class in the target class.
// That is, if the target class declares multiple interceptor classes, an instance of each class is created when the target class
// instance is created. The target class instance and all interceptor class instances are fully instantiated before any
// @PostConstruct callbacks are invoked, and any @PreDestroy callbacks are invoked before the target class and interceptor class
// instances are destroyed.

//-From CDI 1.1 onwards the interceptor can be enabled for the whole application using @Priority annotation.

public class AnInfo {}
