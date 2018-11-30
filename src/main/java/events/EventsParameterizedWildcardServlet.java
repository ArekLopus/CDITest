package events;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;

//-Both will be called if your event type is List<Integer> or List<Number>.
// Although the first observer will fit for add elements to the list (List<? super Integer>)
// while the second will be used to fetch elements from the list (List<? extends Number>).

@WebServlet("/eventParameterizedWildcard")
public class EventsParameterizedWildcardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierInt(86)
	Event<List<Integer>> event1;
	
	@Inject
	@QualifierInt(86)
	Event<List<Number>> event2;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events Observer Parameterized Wildcard</h3>");
		
		out.println("");
		
		List<Integer> list1 = Arrays.asList(1, 3, 2);
		List<Number> list2 = Arrays.asList(3, 2, 1);
		
		event1.fire(list1);
		
		event2.fire(list2);
		
	}
}
