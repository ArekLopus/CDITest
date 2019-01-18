package ejbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sfRemoveMethod")
public class SFRemoveMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	SFRemoveMethod sf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Stateful and @Remove method</h3>");
		
		out.println("@Remove method may only be called by the application if the bean has scope @Dependent.");
		out.println("<br/>For beans with other scopes, the application must let the container destroy the bean.");
		
		sf.remove();
		
	}
	
}
