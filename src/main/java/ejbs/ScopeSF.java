package ejbs;

import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;

//Any scope is available for SF session bean.

@Dependent	
//@ConversationScoped
//@ApplicationScoped
//@SessionScoped
//@RequestScoped
@Stateful
public class ScopeSF {

	public void testEjb() {
		System.out.println("ScopeTestSF.testEjb() called.");
	}

	@PreDestroy
	private void destroy() {
		System.out.println("ScopeSF's @PreDestroy called");
	}
	
	@Remove
	public void remove() {
		System.out.println("ScopeSF's @Remove called");
	}
}
//-Stateful SBs may define a remove method, annotated @Remove, is used by the app to indicate that an instance should be destroyed.
// However, for a contextual instance of the bean — an instance under the control of CDI—this method may only be called by the app
// if the bean has scope @Dependent. For beans with other scopes, the application must let the container destroy the bean.
// Exception
// org.jboss.weld.exceptions.UnsupportedOperationException: WELD-000037: Cannot call EJB remove method directly
// on non-dependent scoped bean public void ejbs.ScopeSF.remove()
