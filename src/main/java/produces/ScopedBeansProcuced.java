package produces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.JustABean;
import qualifiers.QualifierString;
import utils.FindBeanInAScope;

//-The scope of the producer method defaults to @Dependent.
//-Declare a scope by adding a @XxxScoped annotation to the @Produces method.

//-Note: A producer method does not inherit the scope of the bean that declares the method.
// There are two different beans here: the producer method, and the bean which declares it.
//-The scope of the producer method determines how often the method will be called, and lifecycle of the objs returned by the method.
//-The scope of the bean declaring the producer method determines the lifecycle of the obj upon which the producer method is invoked.
@WebServlet("/producesScopedBeans")
public class ScopedBeansProcuced extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	@QualifierString("RequstScoped")
	JustABean jab1;
	
	@Inject
	@QualifierString("SessionScoped")
	JustABean jab2;
	
	@Inject
	@QualifierString("ApplicationScoped")
	JustABean jab3;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<h3>The scope of the producer method defaults to @Dependent scope.<br/>You can declare a different one by adding a @XxxScoped annotation to the @Produces method.</h3>");
		HttpSession session = request.getSession(true);
		System.out.println("Session:" + session.getId());
		
		//Need to call a method to bean be visible.
		out.print("JustABean: " + jab1.testMethod());
		out.print("<br/>JustABean: " + jab2.testMethod());
		out.print("<br/>JustABean: " + jab3.testMethod());
		
		
		out.print("<br/><br/>RequestScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(RequestScoped.class, Any.Literal.INSTANCE));
		out.print("<br/><br/>ApplicationScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(ApplicationScoped.class, Any.Literal.INSTANCE));
		out.print("<br/><br/>SessionScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(SessionScoped.class, Any.Literal.INSTANCE));
		
	}

}
