package injection.point;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/injectionPoint")
public class InjectionPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestCDI ip;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Injection Point Test</h3>");
		
		out.println("Injection Point: " + ip.test());
		
	}
}