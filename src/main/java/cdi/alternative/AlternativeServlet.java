package cdi.alternative;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-@Alternative bean MUST extend another bean or both implement the same interface
//-Needs in beans.xml
//	<alternatives>
//	<class>cdi.alternative.WorkerAlternative</class>
//	</alternatives>
//	OR
//-Use @Priority (on @Alternative class) to make @Alternative work without declaring it in beans.xml!
//	@Priority(Interceptor.Priority.APPLICATION)

//-Difference between @Specialized and @Alternative is that @Specialized class only needs to be ANNOTATED!

@WebServlet("/alternative")
public class AlternativeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	WorkerIntf wr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Alternative</h3>");
		
		out.println("Worker: " + wr.work());
		
	}
	
}
