package events;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-Since CDI 1.1, the container fire events when context are created and destroyed,
// thanks to the provided @Intialized, @Destroyed  and @BeforeDestroyed (2.0) qualifiers. 

//-If your application is deployed in a servlet container, the event payload correspond to the servlet scope object
// corresponding to the initialized or destroyed CDI scope. Otherwise payload will be java.lang.Object 
//-You can observe these event in your application like this (if it’s deployed in a servlet container)
//	public void processRequestScopedInit(@Observes @Initialized(RequestScoped.class) ServletRequest payload) {}
//	public void processRequestScopedBeforeDestroyed(@Observes @BeforeDestroyed(RequestScoped.class) ServletRequest payload) {}
//	public void processRequestScopedDestroyed(@Observes @Destroyed(RequestScoped.class) ServletRequest payload) {}
//  etc, for the other scopes

//Turned off in ObeserversScopeLifecycle!
@WebServlet("/eventScopeLifecycle")
public class EventseScopeLifecycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Built-in events Linked to Scope Lifecycle</h3>");
		
		
		out.println("<br/>");
		
		
		event.fire("Fired by event");
		
	}
}
