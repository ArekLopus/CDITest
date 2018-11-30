package generics;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanParameterized;
import qualifiers.QualifierWithNoMemebers;

//No type erasure for event type
//-It’s not an hidden feature but more something implicit in CDI than can be missed.
//-As CDI is a type centric specification, it does a better job than standard Java regarding parameterized type.
//-For instance take these 2 observer methods:
//	public void processNumberList(@Observes List<Number> event) {  …  }
//	public void processIntegerList(@Observes List<Integer> event) {    …   }
//-The container will make the distinction between both when resolving observer depending of the parameterized type of the event. 

//-And in CDI 1.1+ (wildcards are not allowed in observer event parameter in CDI 1.0) if you declare the following observers :
//	public void processIntegerList(@Observes List<? super Integer> event) {   …   }
//	public void processNumberList(@Observes List<? extends Number> event) {   …   }
//-Both will be called if your event type is List<Integer> or List<Number>.
//Although the first observer will fit for add elements to the list while the second will be used to fetch elements from the list.

//-Wildcards are allowed in observer in CDI 1.1+ if they aren’t in Event injection point.

@WebServlet("/parameterizedEvent")
public class ParameterizedEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Event<TestBeanParameterized<String>> event1;
	
	@Inject
	Event<TestBeanParameterized<String>> event2;

	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Parameterized Event</h3>");
		
		
		TypeLiteral<TestBeanParameterized<String>> typeLiteral = new TypeLiteral<TestBeanParameterized<String>>() {};
		
		event1.fire(new TestBeanParameterized<String>("Testing..."));
		
		//Fires both, with qualifier and no qualifier(subset) 
		event2.select(typeLiteral, new AnnotationLiteral<QualifierWithNoMemebers>() {})
			.fire(new TestBeanParameterized<String>("Testing with select()"));;

	}
}
