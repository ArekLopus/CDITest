package extensions;

//-CDI is intended to be a foundation for frameworks, extensions and integration with other technologies. 
//-Therefore, CDI exposes a set of SPIs for the use of developers of portable extensions to CDI.
//-For example, the following kinds of extensions were envisaged by the designers of CDI:
// •  integration with Business Process Management engines,
// •  integration with third-party frameworks such as Spring, Seam, GWT or Wicket, and
// •  new technology based upon the CDI programming model.

//-A portable extension may integrate with the container by:
// •  Providing its own beans, interceptors and decorators to the container
// •  Injecting dependencies into its own objects using the dependency injection service
// •  Providing a context implementation for a custom scope
// •  Augmenting or overriding the annotation-based metadata with metadata from some other source

//-The CDI extension mechanism provides ways to hook custom code into the lifecycle of the CDI container.

//-The first task a CDI container performs is to load all CDI extensions. 
//-Next, all of the classes contained within JAR files that have a META-INF/beans.xml marker file will be scanned also.
//-For each of these classes an AnnotatedType will be constructed based on the given class.
// This object contains metadata about all annotations, constructors, methods, fields, etc. for the processed class.
// This information can be modified by extensions.
//-Later the CDI container will expose this metadata in Bean<T> instances used to manage contextual objects.

//-The container will start all available contexts after all classes have been scanned and all constraints have been verified.

//-A CDI extension class implements the javax.enterprise.inject.spi.Extension interface.
// This is just a marker interface and thus does not define any methods itself.
//-Instead of using a fixed set of methods statically defined in an interface,
// the container will fire a series of CDI Events during the container lifecycle.
//-The Extension can interact with the CDI container by defining observer methods for such events. 
//-An observer method is a non-static non-final method which has an @Observes parameter with the type of the observed event.
//-It can not only gather information from the CDI container this way;
// it can also modify this information and even transmit back new information to the container. 
//-Extensions can observe the container system events in exactly the same way they observe custom CDI events.
// To find every class that has the @Scheduled annotation, the extension should observe the ProcessAnnotatedType event.
// This system event is fired for every class scanned by the container during boot.
//-As a rule of thumb, all things possible via CDI annotations can also be performed programmatically in an extension.
//-You can very easily add or modify scopes, Interceptors, Decorators, InjectionPoints, Producer methods, ObserverMethods, etc.

//-The Container will pick up our extension via the Java ServiceLoader mechanism.
//-Simply place a file named javax.enterprise.inject.spi.Extension (the same name as the interface) within
// the META-INF/services folder of your JAR and ensure this file contains the fully-qualified name of the Extension implementation. 
//-The container will look up the name of the class at runtime and instantiate it via the default constructor.(The class needs a def constr).

//-The defined container lifecycle events are:
// •  BeforeBeanDiscovery
// •  ProcessAnnotatedType and ProcessSyntheticAnnotatedType
// •  AfterTypeDiscovery
// •  ProcessInjectionTarget and ProcessProducer
// •  ProcessInjectionPoint
// •  ProcessBeanAttributes
// •  ProcessBean, ProcessManagedBean, ProcessSessionBean, ProcessProducerMethod, ProcessProducerField and ProcessSyntheticBean
// •  ProcessObserverMethod and ProcessSyntheticObserverMethod
// •  AfterBeanDiscovery
// •  AfterDeploymentValidation
// •  BeforeShutdown

//-@WithAnnotations causes the container to deliver the events only for the types
// that contain the annotation specified as a parameter.

public class AnInfo {}
