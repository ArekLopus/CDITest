package ejbs;

//-Session beans belong to the EJB specification. They have a special
// lifecycle, state management and concurrency model that is different to other managed beans and non-managed Java objects. 
//-But session beans participate in CDI just like any other bean. 

//-You can inject:
// •  one session bean into another session bean
// •  a managed bean into a session bean
// •  a session bean into a managed bean
// •  have a managed bean observe an event raised by a session bean, and so on.

//-Bean types
// •  The unrestricted set of bean types for a session bean contains all local interfaces of the bean and their superinterfaces.
// •  If the session bean has a bean class local view, the unrestricted set of bean types contains the bean class and all superclasses.
// •  In addition, java.lang.Object is a bean type of every session bean. 
// •  But remote interfaces are not included in the set of bean types.

//-Scopes
//-There’s no reason to explicitly declare the scope of a stateless session bean or singleton session bean.
//-The EJB container controls the lifecycle of these beans, according to the semantics of the @Stateless or @Singleton declaration. 
//-On the other hand, a stateful session bean may have any scope.
// •  A stateless session bean must be part of the @Dependent pseudo-scope
// •  A singleton must be part of either the @ApplicationScoped scope or the @Dependent pseudo-scope
// •  A stateful session bean can declare any scope
// •  Session beans can be generic types. If the session bean class is a generic type, it is important it has scope @Dependent.

//Note: Message-driven and entity beans are by nature non-contextual objects and may not be injected into other objects.
//However, MDBs can take advantage of some CDI functionality, such as dependency injection, interceptors and decorators.
//In fact, CDI will perform injection into any session or message-driven bean, even those which are not contextual instances.

//-Concurency
//-Many beans (including any @SessionScoped or @ApplicationScoped beans) are available for concurrent access. Therefore,
// the concurrency management provided by EJB 3.2 is especially useful. Most session and application scoped beans should be EJBs.

//-When should we use a SB instead of a managed bean? Whenever we need the advanced enterprise services offered by EJB, such as:
// •  method-level transaction management and security,
// •  asynchronous methods,
// •  concurrency management,
// •  instance-level passivation for stateful session beans,
// •  instance-pooling for stateless session beans,
// •  remote or web service invocation,
// •  timers,
// When we don’t need any of these things, an ordinary managed bean will serve just fine.

//-Beans which hold references to heavy-weight resources, or hold a lot of internal state benefit from the advanced
// container-managed lifecycle defined by the EJB SL/SF/singleton model, with its support for passivation and instance pooling.

//-It’s usually obvious when method-level transaction management, method-level security,
// timers, remote methods or asynchronous methods are needed.

//-Stateful SBs may define a remove method, annotated @Remove, is used by the app to indicate that an instance should be destroyed.
// However, for a contextual instance of the bean — an instance under the control of CDI—this method may only be called by the app
// if the bean has scope @Dependent. For beans with other scopes, the application must let the container destroy the bean.
//Exception
// org.jboss.weld.exceptions.UnsupportedOperationException: WELD-000037: Cannot call EJB remove method directly
// on non-dependent scoped bean public void ejbs.ScopeSF.remove()


//-Essentially: use a session bean when you need the services it provides, not just because you want to use dependency injection,
// lifecycle management, or interceptors. Java EE 7 provides a graduated programming model. It’s usually easy to start with
// an ordinary managed bean, and later turn it into an EJB just by adding one of the following annotations:
// @Stateless, @Stateful or @Singleton.

//-On the other hand, don’t be scared to use session beans just because you’ve heard your friends say they’re "heavyweight". 
//-It’s nothing more than superstition to think that something is "heavier" just because it’s hosted natively within the Java EE
// container, instead of by a proprietary bean container or dependency injection framework that runs as an additional layer of
// obfuscation. And as a general principle, you should be skeptical of folks who use vaguely defined terminology like "heavyweight".

//-The main feature of the session beans is passivation. With passivation, a bean is able to temporarily transfer the state of any idle instance to a secondary storage.
//-We are free to write session beans without EJB but they will use a mock passivation. Here is a session bean:
//	@SessionScoped
//	public class SessionBean extends CountBean implements Serializable




public class AnInfo {}
