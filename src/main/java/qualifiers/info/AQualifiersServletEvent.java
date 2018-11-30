package qualifiers.info;

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

import qualifiers.QualifierIntAnnotationLiteral;
import qualifiers.QualifierString;

//-Qualifiers can be applied to an event in one of two ways:
// • by annotating the Event injection point, or
// • by passing qualifiers to the select() of Event.

//-Event qualifiers MAY BE combined, for example
// @Inject @Blog Event<Document> blogEvent;
// blogEvent.select(new AnnotationLiteral<Updated>(){}).fire(document);
//-The above shown event is fired with two qualifiers - @Blog and @Updated. 

//-An observer method is notified if the set of observer qualifiers is a subset of the fired event’s qualifiers.
//	public void afterBlogUpdate(@Observes 		@Updated @Blog Document document) { ... }
//	public void afterDocumentUpdate(@Observes 	@Updated Document document) { ... }
//	public void onAnyBlogEvent(@Observes 		@Blog Document document) { ... }
//	public void onAnyDocumentEvent(@Observes 	Document document) { ... }}}
//-ALL of THESE observer methods will be notified.
@WebServlet("/qualifiersEvent")
public class AQualifiersServletEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString
	Event<StringBuilder> event;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events with Qualifiers</h3>");
		out.println("<p>Important: An observer method is notified if the set of observer qualifiers is a subset of the fired event's qualifiers</p>");
		out.println("<br/><br/>Event is fired with 2 qualifiers (combined annotation and select()) and all 3 observers are notified: No qualfiers, 1 qualifier and 2 qualifiers.");
		
		Annotation qi = new QualifierIntAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			public int value() {
				return 0;
			}
		};
		
		event.select(qi).fire(new StringBuilder("Abc"));
				
	}
}
