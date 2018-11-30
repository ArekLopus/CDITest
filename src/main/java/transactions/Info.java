package transactions;

//-@Transactional provides the application with the ability to control transaction boundaries on CDI Managed Beans,
// as well as Servlets, JAX-RS, and JAX-WS endpoints declaratively. 

//-At both the class and method level, where method level  override those at the class level.

//-Section 7.1 of the EJB 3.2 specification:
// It is illegal to associate JTA transactional interceptors (@Transactional).
//-This restriction may be removed in a future release of this specification.

//-This support is provided via an implementation of CDI interceptors that conduct the necessary suspending, resuming, etc.
// The Transactional interceptor interposes on business method invocations only and not on lifecycle events.
// Lifecycle methods are invoked in an unspecified transaction context.

//-If an attempt is made to call any method of the UserTransaction interface from within the scope of a bean or method annotated with @Transactional and a Transactional.TxType other than NOT_SUPPORTED or NEVER, an IllegalStateException must be thrown.
//-The use of the UserTransaction is allowed within life cycle events.
//-The use of the TransactionSynchronizationRegistry is allowed regardless of any @Transactional annotation.

//-The Transactional interceptors must have a priority of Interceptor.Priority.PLATFORM_BEFORE+200.

//-The TxType element of the annotation indicates whether a bean method is to be executed within a transaction context.
// TxType.REQUIRED is the default.

//-By default checked exceptions do not result in the transactional interceptor marking the transaction for rollback
// and instances of RuntimeException and its subclasses do. 
//-The rollbackOn element can be set to indicate exceptions that must cause the interceptor to mark the transaction for rollback.
//-The dontRollbackOn element can be set to indicate exceptions that must not cause the interceptor to mark the transaction for rollback.
//-When a class is specified for either of these elements, the designated behavior applies to subclasses of that class as well. 
// If both elements are specified, dontRollbackOn takes precedence.

public class Info {}
