package events;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.BeforeDestroyed;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
public class ObeserversScopeLifecycle {

//	public void processApplicationScopedInit(@Observes @Initialized(ApplicationScoped.class) ServletContext payload) {
//		System.out.println("@Initialized(ApplicationScoped.class)");
//	}
//	public void processApplicationScopedBeforeDestroyed(@Observes @BeforeDestroyed(ApplicationScoped.class) ServletContext payload) {
//		System.out.println("@BeforeDestroyed(ApplicationScoped.class)");
//	}
//	public void processApplicationScopedDestroyed(@Observes @Destroyed(ApplicationScoped.class) ServletContext payload) {
//		System.out.println("@Destroyed(ApplicationScoped.class)");
//	}
//	
//	public void processSessionScopedInit(@Observes @Initialized(SessionScoped.class) HttpSession payload) {
//		System.out.println("@Initialized(SessionScoped.class)");
//	}
//	public void processSessionScopedBeforeDestroyed(@Observes @BeforeDestroyed(SessionScoped.class) HttpSession payload) {
//		System.out.println("@BeforeDestroyed(SessionScoped.class)");
//	}
//	public void processSessionScopedDestroyed(@Observes @Destroyed(SessionScoped.class) HttpSession payload) {
//		System.out.println("@Destroyed(SessionScoped.class)");
//	}
//	
//	public void processRequestScopedInit(@Observes @Initialized(RequestScoped.class) ServletRequest payload) {
//		System.out.println("@Initialized(RequestScoped.class)");
//	}
//	public void processRequestScopedBeforeDestroyed(@Observes @BeforeDestroyed(RequestScoped.class) ServletRequest payload) {
//		System.out.println("@BeforeDestroyed(RequestScoped.class)");
//	}
//	public void processRequestScopedDestroyed(@Observes @Destroyed(RequestScoped.class) ServletRequest payload) {
//		System.out.println("@Destroyed(RequestScoped.class)");
//	}
//	
//	public void processConversationScopedInit(@Observes @Initialized(ConversationScoped.class) ServletRequest payload) {
//		System.out.println("@Initialized(ConversationScoped.class)");
//	}
//	public void processConversationScopedBeforeDestroyed(@Observes @BeforeDestroyed(ConversationScoped.class) ServletRequest payload) {
//		System.out.println("@BeforeDestroyed(ConversationScoped.class)");
//	}
//	public void processConversationScopedDestroyed(@Observes @Destroyed(ConversationScoped.class) ServletRequest payload) {
//		System.out.println("@Destroyed(ConversationScoped.class)");
//	}
}
