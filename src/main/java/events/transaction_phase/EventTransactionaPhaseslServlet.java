package events.transaction_phase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/eventTransactionalPhases")
public class EventTransactionaPhaseslServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EventTransactionalEJB eb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Transactional Phase Event</h3>");
		
		out.println("If no transaction is in progress when the event is fired, they are notified at the same time as other observers.");
		
		out.println("<br/><br/>IN_PROGRESS (default) - called when the event is fired");
		out.println("<br/>BEFORE_COMPLETION - called during the before completion phase of the transaction.");
		out.println("<br/>AFTER_COMPLETION - called during the after completion phase of the transaction.");
		out.println("<br/>AFTER_SUCCESS - called during the after completion phase of the transaction, only when the transaction completes successfully.");
		out.println("<br/>AFTER_FAILURE - called during the after completion phase of the transaction, only when the transaction fails.");
		
		eb.transTestSuccess2();
		
	}
}
