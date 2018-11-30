package cdi.specializes;

//-If a bean is specialized by any enabled bean, the first bean is disabled.

//@Specializes
//-Indicates that a bean directly specializes another bean.
//-May be applied to a bean class or producer method.
//-If the second bean has a name, the bean may not declare a name using @Named.
//-If a bean directly specializes a second bean, it inherits:
//  • all qualifiers of the second bean, and
//  • the name, if any, of the second bean.

//-Furthermore, the bean must have all the bean types of the second bean.
//  • If a bean class of a managed bean is annotated @Specializes, then the bean class must directly extend
//    the bean class of a second managed bean. Then the first managed bean directly specializes the second managed bean.
//  • If a bean class of a session bean is annotated @Specializes, then the bean class must directly extend
//    the bean class of a second session bean. Then the first session bean directly specializes the second session bean.
//  • If a producer method is annotated @Specializes, then it must be non-static and directly override another producer method.
//    Then the first producer method directly specializes the second producer method.


//-When we enable an alternative, does that mean the default implementation is disabled? Well, not exactly.
//If the default impl has a qualifier and the alternative does not, you could still inject the default impl.
//-The only way one bean can completely override a second bean at all injection points is if it implements all the bean types
//and declares all the qualifiers of the second bean. 
//However, if the second bean declares a producer method or observer method, then even this is not enough
//to ensure that the second bean is never called! We need something extra.

//-CDI provides a special feature, called specialization, that helps the developer avoid these traps. 
//-Specialization is a way of informing the system of your intent to completely replace and disable an implementation of a bean.

//-When the goal is to replace one bean implementation with a second, to help prevent developer error, the first bean may:
//• directly extend the bean class of the second bean, or
//• directly override the producer method, in the case that the second bean is a producer method,
//and then explicitly declare that it specializes the second bean:
//		@Specializes
//		public class MockCreditCardPaymentProcessor extends CreditCardPaymentProcessor {   …   }

//-When an enabled bean specializes another bean, the other bean is never instantiated or called by the container.
//Even if the other bean defines a producer or observer method, the method will never be called.

//-So why does specialization work, and what does it have to do with inheritance?
//-Since we’re informing the container that our alternative bean is meant to stand in as a replacement for the default impl,
//the alternative impl automatically inherits all qualifiers of the default implementation.
//Thus, in our example, 	MockCreditCardPaymentProcessor inherits the qualifiers @Default and @CreditCard.
//-Furthermore, if the default impl declares a bean EL name using @Named, the name is inherited by the specializing bean.

public class AnInfo {}
