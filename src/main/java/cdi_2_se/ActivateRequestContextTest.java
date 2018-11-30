package cdi_2_se;

import javax.enterprise.context.control.RequestContextController;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import helper.TestBeanRequestScoped;

//-@ApplicationScoped works just fine and as you would expect. One bean per application.
// It's lifecycle starts when you boot container and stops when you shut it down.
//-@SessionScoped at the moment does not work in SE environment as it does not really make any sense there.
//-@RequestScoped does work in SE. This makes sense as "request" can mean more things than just plain old HTTP request.
// However, you will need to take care of activation of this scope.
// Request contexts can be managed either programmatically or via interceptor.
//  • RequestContextController - @Dependent scoped allows to activate and deactivate a request context on the current thread.
//  • @ActivateRequestContext which will activate a request context if not already active prior to the method’s invocation,
//    and deactivate it upon method completion, with the same rules as in RequestContextController.

public class ActivateRequestContextTest {
	
	public ActivateRequestContextTest() {
		try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			
			RequestContextController rcc = container.select(RequestContextController.class).get();
			System.out.println(rcc + "\n");
			
			rcc.activate();

			TestBeanRequestScoped bean = container.select(TestBeanRequestScoped.class).get();
			String info = bean.callMe();
			
			rcc.deactivate();
			
			System.out.println(info);
		}
	}
	
	public static void main(String... args) {
		new ActivateRequestContextTest();
	}	
}