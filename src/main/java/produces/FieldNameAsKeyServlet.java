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

//-Depending on the field name we can return different things.
@WebServlet("/producesFieldName")
public class FieldNameAsKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("fieldNameAsAKeyProducer")
	String fieldOne;
	
	@Inject
	@QualifierString("fieldNameAsAKeyProducer")
	String fieldTwo;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Depending on the field name we can return different things.</h3>");
		
		out.println("Field name is: " + fieldOne);
		out.println("<br/>Field name is: " + fieldTwo);
		
	}
	

	
}
