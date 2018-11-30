package injection.types;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/serverResources")
public class ServerResourcesServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	ServerResources sr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>Resources Injection from Server</h2>");
		
		sr.doSomething();
		
		pw.close();
		
	}

}
