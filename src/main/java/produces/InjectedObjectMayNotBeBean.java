package produces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierString;

//-The objects to be injected are not required to be instances of beans.
//-For example, any JDK class, primitives, a JPA entity, etc.
@WebServlet("/producesString")
public class InjectedObjectMayNotBeBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("stringProducer")
	String str;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>The objects to be injected are not required to be instances of beans.</h3>");
		
		out.println("String produced: " + str);
		
	}
	

	
}
