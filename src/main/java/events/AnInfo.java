package events;

//-Dependency injection enables loose-coupling by allowing the implementation of the injctd bean type to vary,
// either at deployment time or runtime. 
//-Events go one step further, allowing beans to interact with no compile time dependency at all.
// Event producers raise events that are delivered to event observers by the container.

//-This basic schema might sound like the familiar observer/observable pattern, but there are a couple of twists:
// • not only are event producers decoupled from observers; observers are completely decoupled from producers,
// • observers can specify a combination of "selectors" to narrow the set of event notifications they will receive,
// • observers can be notified immediately, or specify that delivery of the event should be delayed until the end of the current transaction.

//-The CDI event notification facility uses more or less the same typesafe approach that we’ve already seen with the DI service.

//Event payload
//-The event object carries state from producer to consumer. 
//-The event object is an instance of a concrete Java class. (The only restriction is that an event type may not contain type variables).
//-An event may be assigned qualifiers, which allows observers to distinguish it from other events of the same type.
// The qualifiers function like topic selectors, allowing an observer to narrow the set of events it observes.
//-An event qualifier is just a normal qualifier, 

//Event observers
//-An observer method is a method of a bean with a parameter annotated @Observes or @ObservesAsync.
//-The annotated parameter is called the event parameter. The type of the event parameter is the observed event type.
//-The event parameter may also specify qualifiers.
//	public void afterDocumentUpdate(@Observes @Updated Document document) { ... }
//-An observer method need not specify any event qualifiers — in this case it is interested in EVERY event whose type
// is assignable to the observed event type. Such observer will trigger on both events shown below:
//	@Inject @Any Event<Document> documentEvent;
//	@Inject @Updated Event<Document> anotherDocumentEvent;
//-If the observer does specify qualifiers, it will be notified of an event if the event object is assignable to the observed
// event type, and if the set of observed event qualifiers is a SUBSET of all the event qualifiers of the event.

//-The observer method may have additional parameters, which are injection points:
//	public void afterDocumentUpdate(@Observes @Updated Document document, User user) { ... }

//Event producers
//-Event producers fire events either synchronously or asynchronously using an instance of the parameterized Event interface.
//-An instance of this interface is obtained by injection:
//	@Inject @Any Event<Document> documentEvent;
//-A producer raises synchronous events by calling the fire() method of the Event interface, passing the event object:
//	documentEvent.fire(document);
//-A producer raises asynchronous events by calling the fireAsync() method of the Event interface, passing the event object:
//	documentEvent.fireAsync(document);
//-The Event.fireAsync() may be called with a NotificationOptions parameter to configure the notification of asynchronous observer


//No type erasure for event type
//-It’s not an hidden feature but more something implicit in CDI than can be missed.
//-As CDI is a type centric specification, it does a better job than standard Java regarding parameterized type.
//-For instance take these 2 observer methods:
//	public void processNumberList(@Observes List<Number> event) {  …  }
//	public void processIntegerList(@Observes List<Integer> event) {    …   }
//-The container will make the distinction between both when resolving observer depending of the parameterized type of the event. 

//-And in CDI 1.1+ (wildcards are not allowed in observer event parameter in CDI 1.0) if you declare the following observers :
//	public void processIntegerList(@Observes List<? super Integer> event) {   …   }
//	public void processNumberList(@Observes List<? extends Number> event) {   …   }
//-Both will be called if your event type is List<Integer> or List<Number>.
// Although the first observer will fit for add elements to the list while the second will be used to fetch elements from the list.

//-Remember that wildcards are allowed in observer in CDI 1.1+ if they aren’t in Event injection point.


//Qualifiers don’t work with event as they work with beans
//-That’s an important point that was very badly explained in the spec before CDI 1.2. 
//-Developers often assume that Event<> and Instance<> have quite similar way of functioning regarding qualifiers,
// that’s a big mistake. But let’s start with the most important here :
//-An observer matches an event if its type is in the event types set
// and if the observer has a SUBSET of the qualifier of the event.
//-Event<> is here to build events not filter them.
//-Again Event looks like Instance but is very different. While Instance is a tool to lookup for beans, 
//-Event is a tool to build an event from an object and qualifiers. So keep in mind that when you use
// Event.select(Annotation... qualifiers) you are adding qualifier to the event you’ll be firing.


//Built-in events linked to scope lifecycle (CDI 1.1+)
//-Since CDI 1.1, the container fire events when context are created and destroyed,
// thanks to the provided @Intialized, @Destroyed  and @BeforeDestroyed (2.0) qualifiers. 
//-If your application is deployed in a servlet container, the event payload correspond to the servlet scope object
// corresponding to the initialized or destroyed CDI scope. Otherwise payload will be java.lang.Object 
//-You can observe these event in your application like this (if it’s deployed in a servlet container) :
//	public void processApplicationScopedInit(@Observes @Initialized(ApplicationScoped.class) ServletContext payload) {}
//	public void processApplicationScopedDestroyed(@Observes @Destroyed(ApplicationScoped.class) ServletContext payload) {}
// etc, for the other scopes

//Events Metadata (CDI 1.1+)
//-Version 1.1 of the spec introduced EventMetadata interface. 
//-It allows an observer to get all the metadata about an event. You get the EventMetadata by adding it to the observer parameters:
//	public void processPayload(@Observes Payload event, EventMetadata meta) {}
//-The EventMetadata contains the following methods:
// •  getQualifiers() returns the set of qualifiers with which the event was fired.
// •  getInjectionPoint() returns the InjectionPoint from which events payload was fired, or null if from fromBeanManager.fireEvent(…).
// •  getType() returns the type representing runtime class of the event object with type variables resolved.

//-This bring a solution to add more fine-grained filtering on observer execution depending on actual metadata of the triggered event


//The plugin Pattern
//-We saw that CDI event data is totally free. You can choose any object (again avoid no dependent bean) to fire an event
// and this object will be received as a playlod by each observer matching the event type and qualifier.
//-An other interesting fact is that this payload is mutable and can be modified by its observers.
// Following this idea, observers can become a way to enrich a given object with new data.
//-We can use this approach to seamlessly enhance content by adding a CDI archive to an existing application.


//The catch them all pattern
//-Need to observe all fired event and have their info (for logging purpose for instance), you only have to observe Object.
//	public void processPayload(@Observes Object event, EventMetadata meta) {}
//-EventMetadata will even help you to know in which bean the event was fired.
// A nice way to build a bridge with a messaging service (did I say JMS? ;) )


//Don’t forget Interceptors and Decorators
//-While it’s forbidden to declare observer in decorators or interceptors, you can still fire event from them.
//-So they can be used to enhance existing bean and add event triggering to them without touching their code.
// A nice way to add event notification only when needed.

public class AnInfo {}
