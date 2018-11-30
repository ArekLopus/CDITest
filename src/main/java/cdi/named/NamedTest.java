package cdi.named;

import javax.inject.Named;

//-If you want to reference a bean in non-Java code that supports Unified EL expressions,
// fe, in a JSP or JSF page, you must assign the bean an EL name.
//-The EL name is specified using the @Named annotation.
//	@Named
//	public class ShoppingCart {}
//-The name defaults to the unqualified class name, decapitalized; in this case, shoppingCart.
//-Note: The @Named annotation is not what makes the class a bean. Most classes in a bean archive are already recognized as beans.
// The @Named annotation just makes it possible to reference the bean from the EL, most commonly from a JSF view.

@Named 
public class NamedTest {
}
