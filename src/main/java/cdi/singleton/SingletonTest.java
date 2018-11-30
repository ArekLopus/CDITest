package cdi.singleton;

import javax.inject.Singleton;

//-CDI also supports two pseudo-scopes. @Singleton and  @Dependent
//-Beans with scope @Singleton don’t have a proxy object.
//-Clients hold a direct reference to the singleton instance. We need to consider the case of a client that can be serialized, fe,
// any bean with scope @SessionScoped or @ConversationScoped,
// any dependent object of a bean with scope @SessionScoped or @ConversationScoped,
// or any stateful session bean.

//-Now, if the singleton instance is a simple, immutable, serializable object like a string, a number or a date,
// we probably don’t mind too much if it gets duplicated via serialization.
// However, that makes it stop being a true singleton, and we may as well have just declared it with the default scope.

//-There are several ways to ensure that the singleton bean remains a singleton when its client gets serialized:
// • have the singleton bean implement writeResolve() and readReplace() (as defined by the Java serialization specification),
// • make sure the client keeps only a transient reference to the singleton bean, or
// • give the client a reference of type Instance<X> where X is the bean type of the singleton bean.

//-A fourth, better solution is to instead use @ApplicationScoped, allowing the container to proxy the bean,
// and take care of serialization problems automatically. 
//-@ApplicationScoped - The CDI application scope which guarantee that the class is instantiated only once.
// This is preferable because is much simpler to develop, test and maintain.
// So, when CDI is available, use this for having a singleton "surrogate" that provides a single instance and app scoped data.

@Singleton
public class SingletonTest {

	

}
