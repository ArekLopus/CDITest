package utils;

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
import helper.TestBeanApplicationScoped;
import helper.TestBeanRequestScoped;
import helper.TestBeanSessionScoped;
import qualifiers.QualifierString;

@WebServlet("/findBeanInScope")
public class FindBeanInAScopeServlet extends HttpServlet {
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
	
	@Inject
	TestBeanRequestScoped rtb;
	
	@Inject
	TestBeanApplicationScoped atb;
	
	@Inject
	TestBeanSessionScoped stb;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<h3>Find Beans In A Scope</h3>");
		HttpSession session = request.getSession(true);
		System.out.println("Session:" + session.getId());
		
		//A bean needs to call a method to be visible!
		rtb.callMe();
		atb.callMe();
		stb.callMe();
		
		out.print("JustABean: " + jab1.testMethod());
		out.print("<br/>JustABean: " + jab2.testMethod());
		out.print("<br/>JustABean: " + jab3.testMethod());
		
		
		out.print("<br/><br/>RequestScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(RequestScoped.class, Any.Literal.INSTANCE));
		out.print("<br/><br/>AppScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(ApplicationScoped.class, Any.Literal.INSTANCE));
		out.print("<br/><br/>SessionScoped<br/>");
		out.print(FindBeanInAScope.getAllScopedBeans(SessionScoped.class, Any.Literal.INSTANCE));
		
	}

}
