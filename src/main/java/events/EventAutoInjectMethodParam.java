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

//-The observer method may have additional parameters, which are injection points:
//	public void afterDocumentUpdate(@Observes @Updated Document document, User user) { ... }
@WebServlet("/eventAutoInjectMethodParam")
public class EventAutoInjectMethodParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("Testing Auto Injected Method Params")
	Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events Observer Auto Injects Method Params</h3>");
		
		event.fire("Fired by event");
		
	}
}
