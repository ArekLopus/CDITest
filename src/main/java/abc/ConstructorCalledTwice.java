package abc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.interceptors.InterceptorsTestBean;

//https://stackoverflow.com/questions/18378608/why-is-constructor-of-cdi-bean-class-called-more-than-once/18387485
//https://stackoverflow.com/questions/18911646/constructor-of-cdi-managed-bean-is-invoked-twice-while-opening-the-page

//-The constructor of managed beans may be called by the container also for creating proxies.

//InterceptorsTestBean - constructor called, object cdi.interceptors.InterceptorsTestBean$Proxy$_$$_WeldClientProxy@5a880a]]
//InterceptorsTest's @AroundConstruct
//InterceptorsTestBean - constructor called, object cdi.interceptors.InterceptorsTestBean$Proxy$_$$_WeldSubclass@fb637d]]
//InterceptorsTest's @PostConstruct]]

@WebServlet("/constructorCalledTwice")
public class ConstructorCalledTwice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	InterceptorsTestBean it;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Constructor Called Twice</h3>");
		
		it.testMethod();
		
	}
	
}
