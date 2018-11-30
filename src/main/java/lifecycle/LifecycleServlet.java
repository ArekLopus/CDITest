package lifecycle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. @AroundConstruct 
//2. constructor 
//3. Dependency Injection 
//4. @PostConstruct 
//5. Business Methods invocations, @AroundInvoke 
//6. @PreDestroy

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	LifecycleTestBean lc;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Lifecycle</h3>");
		
		out.println("1. @AroundConstruct");
		out.println("<br/>2. constructor");
		out.println("<br/>3. Dependency Injection");
		out.println("<br/>4. @PostConstruct");
		out.println("<br/>5. Business Methods invocations, @AroundInvoke");
		out.println("<br/>6. @PreDestroy");
		out.println("");
		
		lc.callMe();
	}
}
