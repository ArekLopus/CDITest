package cdi_lookup_programmatically.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ab -c 5 -n 50000 http://localhost:8080/CDITest/testInjected
//ab -c 1 -n 20000 http://localhost:8080/CDITest/testInjected
@WebServlet("/testInjected")
public class TestInjectedReqScopped extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestBeanReq rb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Test Injected Bean</h3>");
		
		out.println("Bean: " + rb.callMe());
		
	}
}