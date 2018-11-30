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

//-A producer field is really just a shortcut that lets us avoid writing a useless getter method.
// However, in addition to convenience, producer fields serve a specific purpose as an adaptor for Java EE component env injection,

//-A producer field is a simpler alternative to a producer method.
//-A producer field is declared by annotating a field of a bean class with the @Produces, the same annotation as for methods.
//-The rules for determining the bean types of a producer field parallel the rules for producer methods.

//-A producer field must be a field of a managed bean class or SB class.
// A producer field  may be either static or non-static (for not EJB).
// If the bean is a SB, the producer field must be a static field of the bean class.
@WebServlet("/producesField")
public class FieldProcucer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("fieldProducer")
	String str;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>A producer field is really just a shortcut that lets us avoid writing a useless getter method. .</h3>");
		
		out.println("Injected String: " + str);
		
	}
	

	
}
