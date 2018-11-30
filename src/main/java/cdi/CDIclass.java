package cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierWithNoMemebers;

//-Provides access to the current container.
//-CDI implements Instance and therefore might be used to perform programmatic lookup.
// If no qualifier is passed to Instance.select(java.lang.annotation.Annotation...) method, the @Default qualifier is assumed.

//Methods:	current(), getBeanManager(), setCDIProvider,
// 			get, destroy, isAmbiguous, isResolvable, isUnsatisfied, select, select, select, stream

@WebServlet("/cdiClass")
public class CDIclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Instance<String> str;
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>CDI class Test</h3>");
		out.println("<h4>Provides access to the current container.</h4>");
		
		str = CDI.current().select(String.class, new AnnotationLiteral<QualifierWithNoMemebers>() {} );
		
		out.println("<br/>Selected: " + str.get());
	}
}
