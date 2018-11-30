package produces;

//-A producer method is a method that acts as a source of bean instances. The method declaration itself describes the bean and
// the container invokes the method to obtain an instance of the bean when no instance exists in the specified context. 

//-Producer methods let us overcome certain limitations that arise when a container, instead of the application, is responsible
// for instantiating objects. They’re also the easiest way to integrate objects which are not beans into the CDI environment.
//-A producer method acts as a source of objects to be injected, where:
//  • the objects to be injected are not required to be instances of beans (expose a JPA entity, any JDK class as a bean)
//  • the concrete type of the objects to be injected may vary at runtime.
//  • the objects require some custom initialization that is not performed by the bean constructor.
//  • define multiple beans, with different scopes or initialization, for the same implementation class.

//	@Produces
//-Identifies a producer method or field. May be applied to a method or field of a bean class.
//-A bean may declare multiple producer methods or fields.
//-Producer methods and fields are not inherited by bean subclasses.
//-Interceptors and decorators may not declare producer methods or fields.

//-A producer method must be a non-abstract method of a managed bean class or session bean class. 
// A producer method may be either static or non-static. 
// If the bean is a SB, the producer method must be either a business method of the EJB or a static method of the bean class.
//-A producer method may have any number of parameters. All producer method parameters are injection points.

//-A producer field must be a field of a managed bean class or SB class.
// A producer field  may be either static or non-static (for not EJB).
// If the bean is a SB, the producer field must be a static field of the bean class.

//-If a producer method sometimes returns a null value, or if a producer field sometimes contains a null value when accessed,
// then the producer method or field must have scope @Dependent.
//-A producer method return type or producer field type may not be a type variable (may be a primitive).
//-If the producer method return type or producer field type is a parameterized type,
// it must specify an actual type parameter or type variable for each type parameter.
//-If the producer method return type or producer field type is a parameterized type with a type variable,
// it must have scope @Dependent.

public class AnInfo {}
