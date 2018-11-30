package ejbs;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

//-There’s no reason to explicitly declare the scope of a stateless session bean or singleton session bean.
//-The EJB container controls the lifecycle of these beans, according to the semantics of the @Stateless or @Singleton declaration.

//@ConversationScoped
//@ApplicationScoped
//@SessionScoped
//@RequestScoped
//-For other scopes:
//	org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-000082:
//	Scope interface javax.enterprise.context.RequestScoped is not allowed on stateless session beans for class ejbs.ScopeSL.
//	Only @Dependent is allowed. -- WELD-000082: Scope interface javax.enterprise.context.RequestScoped is not allowed
//	on stateless session beans for class ejbs.Abc. Only @Dependent is allowed.

//Only @Dependent pseudo-scope is allowed.
@Dependent
@Stateless
public class ScopeSL {

	public void testEjb() {
		System.out.println("ScopeTestSL.testEjb() called.");
	}

}
