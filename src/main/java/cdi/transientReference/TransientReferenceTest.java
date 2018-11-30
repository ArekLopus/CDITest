package cdi.transientReference;

//-A session scoped can inject a dependent bean only if there is this annotation.
// If we remove the annotation, we get an error because the order is not serializable.

//-If a parameter annotated with @TransientReference resolves to a dependent scoped bean,
// then the bean will be destroyed after the invocation completes.

//public class OrderManager {
//    @Inject 
//    public OrderManager(@TransientReference Order order) {
//       ...
//    }
//}

//-Non-serializable beans that are only required for bean initialization can be injected
// into constructor or intializer methods with the @TransientReference annotation.
//-These @TransientReference dependent scoped beans will be be destroyed at the end of the method invocation.
public class TransientReferenceTest {
	
}
