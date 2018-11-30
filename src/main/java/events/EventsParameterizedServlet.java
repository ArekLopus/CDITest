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

//No type erasure for event type
//-As CDI is a type centric specification, it does a better job than standard Java regarding parameterized type.
//-For instance take these 2 observer methods:
//	public void processNumberList(@Observes List<Number> event) {  …  }
//	public void processIntegerList(@Observes List<Integer> event) {    …   }
//-The container will make the distinction between both when resolving observer depending of the parameterized type of the event. 

//-And in CDI 1.1+ (wildcards are not allowed in observer event parameter in CDI 1.0) if you declare the following observers :
//	public void processIntegerList(@Observes List<? super Integer> event) {   …   }
//	public void processNumberList(@Observes List<? extends Number> event) {   …   }
//-Both will be called if your event type is List<Integer> or List<Number>.
// Although the first observer will fit for add elements to the list while the second will be used to fetch elements from the list.

//-Wildcards are allowed in observer in CDI 1.1+ if they aren’t in Event injection point.
@WebServlet("/eventParameterized")
public class EventsParameterizedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//@Inject
	//Event<List<? extends Number>> event;		//Does not work for Integer or Number
	//Event<List<? super Integer>> event;		//Does not work for Integer or Number	
	//Event<List> event;		//org.jboss.weld.exceptions.IllegalArgumentException: WELD-000819: 
								//Cannot provide an event type parameterized with a type parameter class java.util.Arrays$ArrayList<E>	
	
	@Inject
	@QualifierInt(81)
	Event<List<Integer>> event1;
	
	@Inject
	@QualifierInt(81)
	Event<List<Number>> event2;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Events Observer Parameterized</h3>");
		
		out.println("");
		
		List<Integer> list1 = Arrays.asList(1, 3, 2);
		List<Number> list2 = Arrays.asList(3, 2, 1);
		
		event1.fire(list1);
		event2.fire(list2);
		
	}
}
