package configurators;

//-CDI 2.0 adds the Configurators API - a new way to configure some parts of the SPI during container lifecycle event notification.
//-E.g. to add a qualifier to a bean, an extension can observe ProcessBeanAttributes, then obtain a configurator instance
// through ProcessBeanAttributes.configureBeanAttributes() and finally use BeanAttributesConfigurator.addQualifier(Annotation).
//-No need to wrap/delegate to the original BeanAttributes. See also chapter Configurators interfaces of the CDI specification.
//	ProcessAnnotatedType.configureAnnotatedType()
//	ProcessBeanAttributes.configureBeanAttributes()
//	ProcessInjectionPoint.configureInjectionPoint()
//	ProcessObserverMethod.configureObserverMethod()
//	ProcessProducer.configureProducer()

//-InterceptionFactory.configure() - returns an AnnotatedTypeConfigurator
//	Set<AnnotatedConstructorConfigurator<TesterBean>> constructors = factory.configure().constructors();
//	Set<AnnotatedFieldConfigurator<? super TesterBean>> fields = factory.configure().fields();
//	Set<AnnotatedMethodConfigurator<? super TesterBean>> methods = factory.configure().methods();
//	AnnotatedTypeConfigurator<TesterBean> removeAll = factory.configure().removeAll();

//-CDI 2.0 introduced the following Configurators interface:
// • AnnotatedTypeConfigurator SPI for AnnotatedType configuration. Helps create and configure the following type metadata:
//	  • AnnotatedType    • AnnotatedField    • AnnotatedConstructor    • AnnotatedMethod    • AnnotatedParameter
// • InjectionPointConfigurator interface - helps configure an existing InjectionPoint instance
// • BeanAttributesConfigurator interface - helps configure a new BeanAttributes instance
// • BeanConfigurator interface - helps configure a new Bean instance
// • ObserverMethodConfigurator(.EventConsumer<T>) interface - helps configure an ObserverMethod instance
// • ProducerConfigurator interface - helps configure a Producer instance

//-The container must provide implementation for all these configurators and make them available in matching container lifecycle events as defined in Container lifecycle events.

// • InterceptionFactory
//-In CDI 2.0, it is possible to add Interceptor to a producer programmaticially.
//-InterceptionFactory allows to create a wrapper instance whose method invocations
// are intercepted by method interceptors and forwarded to a provided instance.
//-An implementation of InterceptionFactory may be obtained by calling
// BeanManager.createInterceptionFactory(CreationalContext, Class) to be used in the create method of a custom bean for example.

public class Info {}
