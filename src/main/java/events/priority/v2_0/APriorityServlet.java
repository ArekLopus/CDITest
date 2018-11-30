package events.priority.v2_0;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;

//-Observers with the same priority are invoked in an unpredictable order
//-The default order is javax.interceptor.Interceptor.Priority.APPLICATION + 500.
//-@Priority specifies the order in which observers should be called, smaller numbers first.

@WebServlet("/eventPriority")
public class APriorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierInt(99)
	Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events with Priorities</h3>");
		
		event.fire("Fired by event");
		
	}
}
