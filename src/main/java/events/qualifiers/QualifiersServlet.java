package events.qualifiers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;
import qualifiers.QualifierIntAnnotationLiteral;

//-Qualifiers can be applied to an event in one of two ways:
// • by annotating the Event injection point, or
// • by passing qualifiers to the select() of Event.

//-Event qualifiers may be combined, for example
// @Inject @Blog Event<Document> blogEvent;
// blogEvent.select(new AnnotationLiteral<Updated>(){}).fire(document);
//-The above shown event is fired with two qualifiers - @Blog and @Updated. 

//-An observer method is notified if the set of observer qualifiers is a subset of the fired event’s qualifiers.
//	public void afterBlogUpdate(@Observes 		@Updated @Blog Document document) { ... }
//	public void afterDocumentUpdate(@Observes 	@Updated Document document) { ... }
//	public void onAnyBlogEvent(@Observes 		@Blog Document document) { ... }
//	public void onAnyDocumentEvent(@Observes 	Document document) { ... }}}
//-ALL of THESE observer methods will be notified.
@WebServlet("/eventQualifiers")
public class QualifiersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierInt(1)
	Event<String> event1;
	
	@Inject
	Event<String> event2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events with Qualifiers</h3>");
		out.println("<p>Important: An observer method is notified if the set of observer qualifiers is a subset of the fired event's qualifiers</p>");
		
		
		event1.fire("Event Annotated with Qualifier at injection point");
		
		Annotation qt = new QualifierIntAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			public int value() {
				return 2;
			}
		};
		event2.select(String.class, qt).fire("Qualifier is Passed via event.select(class, qualifier)");
		event2.select(qt).fire("Qualifier is Passed via event.select(qualifier)");

		//Works if qualifier is without any members
		//event.select(new AnnotationLiteral<QualifierTest>(){}).fire("Just Testing");
				
		//If qualifier with memebers, AnnotationLiteral must implement qualifier! 
		//java.lang.RuntimeException: class aa.QualifiersServlet2$2
		//does not implement the annotation type with members qualifiers.QualifierInt
		//event.select(new AnnotationLiteral<QualifierInt>(){
		//	public int value() {  return 2;  }
		//}).fire("abc");		
		
	}
}
