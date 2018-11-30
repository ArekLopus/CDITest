package cdi_lookup_programmatically.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ab -c 5 -n 50000 http://localhost:8080/CDITest/testProgrammatically
//ab -c 1 -n 20000 http://localhost:8080/CDITest/testProgrammatically
@WebServlet("/testProgrammatically")
public class TestProgrammatically extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Instance<TestBeanReq> rb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Test Look up Bean</h3>");
		
		
		rb = CDI.current().select(TestBeanReq.class);
		
		out.println("Bean: " + rb.get().callMe());
		
	}
}