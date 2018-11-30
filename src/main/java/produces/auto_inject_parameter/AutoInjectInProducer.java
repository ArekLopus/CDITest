package produces.auto_inject_parameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;

@WebServlet("/autoinject")
public class AutoInjectInProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	@QualifierInt(222)
	Instance<String> str;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<h3>Producers Auto Inject Parameters into their methods</h3>");
		
		out.print("<br/>String with @QualifierTest(222) -> " + str.get());
		
		System.out.println(str);
	}


}
