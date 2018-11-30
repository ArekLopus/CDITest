package cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierString;
import qualifiers.QualifierWithNoMemebers;

//-Allows the application to dynamically obtain instances of beans with a specified combination of required type and qualifiers.

//-In certain situations, injection is not the most convenient way to obtain a contextual reference. Fe, it may not be used when:
// • the bean type or qualifiers vary dynamically at runtime, or
// • depending upon the deployment, there may be no bean which satisfies the type and qualifiers, or
// • we would like to iterate over all beans of a certain type.

//-Find all beans that match a given type or programmatic lookup to resolve a bean at runtime thanks to the Instance intf.
// 1. Instance injection points are always satisfied and never fail at deployment time
// 2. Instance provides test methods to know if requesting an instance is safe
// 3. With Instance you control when a bean instance is requested with the get() method

//Methods:	get, destroy, isAmbiguous, isResolvable, isUnsatisfied, select, select, select, stream

@WebServlet("/instance")
public class InstanceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("stringProducer")
	Instance<String> str1;
	
	Instance<String> str2;
	
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Instance Test</h3>");
		out.println("<h4>Methods: get, destroy, isAmbiguous, isResolvable, isUnsatisfied, select, select, select, stream</h4>");
		
		str2 = CDI.current().select(String.class, new AnnotationLiteral<QualifierWithNoMemebers>() {} );
		
		System.out.println(str1.isAmbiguous());
		System.out.println(str1.isResolvable());
		System.out.println(str1.isUnsatisfied());
		
		System.out.println(str1.iterator());
		
		System.out.println(str1.stream());
		
		//System.out.println(str1.destroy(instance));
		
		out.println("<br/>String: " + str1.get());
		out.println("<br/>String: " + str2.get());
	}
}
