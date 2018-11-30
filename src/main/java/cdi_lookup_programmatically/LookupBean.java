package cdi_lookup_programmatically;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanApplicationScoped;

//-We lookup programatically for a bean.

@WebServlet("/lookupProgrammatically")
public class LookupBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Programmatically Looking Up a Bean.</h3>");
		
		
		Instance<TestBeanApplicationScoped> tb = CDI.current().select(TestBeanApplicationScoped.class);
		
		out.println("tb.isAmbiguous() -> " + tb.isAmbiguous());
		out.println("<br/>tb.isResolvable() -> " + tb.isResolvable());
		out.println("<br/>tb.isUnsatisfied() -> " + tb.isUnsatisfied());
		
		TestBeanApplicationScoped testBean = tb.get();
		System.out.println(testBean.callMe());
		out.print("<br/>Method call: " + testBean.callMe());
		
		out.println("<br/>Destroyed");
		tb.destroy(tb.get());
		
		System.out.println("ForEach:");
		tb.forEach(System.out::println);
		
		System.out.println(testBean.callMe());
		

	}
	
}
