package cdi.managedbean;

import javax.annotation.ManagedBean;

// (javax.annotation.ManagedBean) 
//-The ManagedBean annotation marks a POJO (Plain Old Java Object)as a ManagedBean.
//-A ManagedBean supports a small set of basic servicessuch as resource injection, lifecycle callbacks and interceptors.

//-You can explicitly declare a managed bean by annotating the bean class @ManagedBean, but in CDI you don’t need to.
//-A managed bean is a Java class. The basic lifecycle and semantics of a managed bean are defined by the Managed Beans spec.
//-According to the specification, the CDI container treats any class that satisfies the following conditions as a managed bean:
//	 • It is not a non-static inner class.
//	 • It is a concrete class, or is annotated @Decorator.
//	 • It is not annotated with an EJB component-defining annotation or declared as an EJB bean class in ejb-jar.xml.
//	 • It does not implement javax.enterprise.inject.spi.Extension.
//	 • It has an appropriate constructor—either:
//	    • the class has a constructor with no parameters, or
//	    • the class declares a constructor annotated @Inject.

//-Note: According to this definition, JPA entities are technically managed beans. However, entities have their own special
// lifecycle, state and identity model and are usually instantiated by JPA or using new. Therefore we don’t recommend
// directly injecting an entity class. We especially recommend against assigning a scope other than @Dependent to an entity class,
// since JPA is not able to persist injected CDI proxies.

//-Session beans (EJB) are also, technically, managed beans. However, since they have their own special lifecycle
// and take advantage of additional enterprise services, the CDI specification considers them to be a different kind of bean.

//-If a managed bean has a public field, it must have the default scope @Dependent.

//-Managed beans support the @PostConstruct and @PreDestroy lifecycle callbacks.

@ManagedBean
public class ManagedBeanTest {
}
