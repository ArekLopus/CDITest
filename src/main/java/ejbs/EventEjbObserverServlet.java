package ejbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanEJB;


@WebServlet("/eventEjb")
public class EventEjbObserverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Event<TestBeanEJB> event;
	
	@Inject
	TestBeanEJB tb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Event Fires EJB</h3>");
		
		event.fire(tb);
		
		out.println("<br/>");
	}
}
