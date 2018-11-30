package cdi.stereotype;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

import cdi.interceptor_binding.TestBinding;

//-A stereotype is an annotation, annotated @Stereotype, that packages several other annotations.
//-A bean may declare zero, one or multiple stereotypes.
// Stereotype annotations may be applied to a bean class / producer method / field.


//-A stereotype encapsulates any combination of:
//  • a default scope, and
//  • a set of interceptor bindings.
//-A stereotype may also specify that:
//  • all beans with the stereotype have defaulted bean names, or that
//  • all beans with the stereotype are alternatives.

//-A particular annotation may still override the default of Stereotype if necessary:
//	@Dependent @Action (was @RequestScoped)
//	public class DependentScopedLoginAction { ... }

//-Stereotypes may declare other stereotypes, which we’ll call stereotype stacking.
// You may want to do this if you have 2 distinct stereotypes which are meaningful on their own,
// but in other situation may be meaningful when combined.

//-CDI defines one standard stereotype, @Model, which is expected to be used frequently in web applications:
//	@Named @RequestScoped

@TestBinding
@Stereotype
@RequestScoped
@Named
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface MyStereotype {}

