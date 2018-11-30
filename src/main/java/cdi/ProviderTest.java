package cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierString;
import qualifiers.QualifierWithNoMemebers;

//-For on-demand injection of components / resources inject them as Provider:

//-javax.inject.Provider is the minimalistic version of the javax.enterprise.inject.Instance intf.
// In fact Instance inherits from Provider
//-Instance comes with additional functionality.

//-Provides instances of T. Typically implemented by an injector. For any type T that can be injected, you can also inject Provider<T>. 
//-Compared to injecting T directly, injecting Provider<T> enables:
// • retrieving multiple instances.
// • lazy or optional retrieval of an instance.
// • breaking circular dependencies.
// • abstracting scope so you can look up an instance in a smaller scope from an instance in a containing scope.

//-Methods:	get() - Provides a fully-constructed and injected instance of T.

@WebServlet("/provider")
public class ProviderTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("stringProducer")
	Provider<String> str1;
	
	Provider<String> str2;
	
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Provider Test</h3>");
		out.println("<h4>Method: get</h4>");
		out.println("<h4>For on-demand injection of components / resources inject them as Provider</h4>");
		
		str2 = CDI.current().select(String.class, new AnnotationLiteral<QualifierWithNoMemebers>() {} );
		
		
		out.println("<br/>String: " + str1.get());
		out.println("<br/>String: " + str2.get());
	}
}
