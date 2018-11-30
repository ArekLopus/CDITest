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

import qualifiers.QualifierString;

//Events Metadata (CDI 1.1+)
//-Version 1.1 of the spec introduced EventMetadata interface. 
//-It allows an observer to get all the metadata about an event. You get the EventMetadata by adding it to the observer parameters:
//	public void processPayload(@Observes Payload event, EventMetadata meta) {}
//-The EventMetadata contains the following methods:
// •  getQualifiers() returns the set of qualifiers with which the event was fired.
// •  getInjectionPoint() returns the InjectionPoint from which events payload was fired, or null if from fromBeanManager.fireEvent(…).
// •  getType() returns the type representing runtime class of the event object with type variables resolved.

//-This bring a solution to add more fine-grained filtering on observer execution depending on actual metadata of the triggered event

@WebServlet("/eventMetadata")
public class EventsMetadata extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("Metadata")
	Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events Observer Metadata</h3>");
		
		out.println("observerWithMetadata(@Observes @QualifierString('Metadata') String str, EventMetadata meta)");
		
		out.println("<br/><br/>InjectionPoints: [BackedAnnotatedField] @Inject @QualifierString events.EventsMetadata.event");
		out.println("<br/>Qualifiers: [@javax.enterprise.inject.Any(), @qualifiers.QualifierString(value=Metadata)");
		out.println("<br/>Type: class java.lang.String");
		
		
		event.fire("Fired by event");
		
	}
}
