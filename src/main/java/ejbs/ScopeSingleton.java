package ejbs;

import javax.ejb.Singleton;

//-There’s no reason to explicitly declare the scope of a stateless session bean or singleton session bean.
//-The EJB container controls the lifecycle of these beans, according to the semantics of the @Stateless or @Singleton declaration. 

//-A singleton must be part of either the @ApplicationScoped scope or the @Dependent pseudo-scope
//@ApplicationScoped
//@Dependent

//@ConversationScoped
//@SessionScoped
//@RequestScoped
//-For other scopes:
//org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-000083:
//Scope interface javax.enterprise.context.ConversationScoped is not allowed on singleton session beans for
//class ejbs.ScopeSingleton. Only @Dependent and @ApplicationScoped is allowed.
//-- WELD-000083: Scope interface javax.enterprise.context.ConversationScoped is not allowed on singleton session beans
//for class ejbs.ScopeSingleton. Only @Dependent and @ApplicationScoped is allowed.

@Singleton
public class ScopeSingleton {

	public void testEjb() {
		System.out.println("ScopeTestSL.testEjb() called.");
	}

}
