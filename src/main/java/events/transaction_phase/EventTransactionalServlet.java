package events.transaction_phase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/eventTransactional")
public class EventTransactionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EventTransactionalEJB eb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Transactional Phase Event</h3>");
		
		out.println("First Test Fails.");
		out.println("<br/>Second is Successful.");
		
		eb.transTestFail();
		eb.transTestSuccess();
	}
}
