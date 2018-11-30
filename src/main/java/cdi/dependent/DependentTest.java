package cdi.dependent;

import javax.enterprise.context.Dependent;

//-CDI also supports two pseudo-scopes. @Singleton and @Dependent

//-@Dependent is the default scope for a bean which does not explicitly declare a scope type.
//-For example, this bean has the scope type @Dependent:
//    public class Calculator { ... }
//-An instance of a dependent bean is never shared between different clients or different injection points.
// It is strictly a dependent object of some other object.
// It is instantiated when the object it belongs to is created, and destroyed when the object it belongs to is destroyed.
//-Beans with scope @Dependent don’t need a proxy object. The client holds a direct reference to its instance.
//-CDI makes it easy to obtain a dependent instance of a bean, even if the bean is already declared as a bean with some other scope type.

//-If a Unified EL expression refers to a dependent bean by EL name, an instance of the bean is instantiated every time
// the expression is evaluated. The instance is not reused during any other expression evaluation.
//-Note: If you need to access a bean directly by EL name in a JSF page, you probably need to give it a scope other than @Dependent.
// Otherwise, any value that gets set to the bean by a JSF input will be lost immediately.
// That’s why CDI features the @Model stereotype; it lets you give a bean a name, and set its scope to @RequestScoped in one stroke.
// If you need to access a bean that really has to have the scope @Dependent from a JSF page,
// inject it into a different bean, and expose it to EL via a getter method.

@Dependent				//Not needed, default scope for a bean which does not explicitly declare a scope type. 
public class DependentTest {
}
