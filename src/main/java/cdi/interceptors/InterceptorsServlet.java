package cdi.interceptors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/interceptors")
public class InterceptorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	InterceptorsTestBean it;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Interceptors Test</h3>");
		
		it.testMethod();
		
	}
	
}
