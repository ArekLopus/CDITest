package cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;
import qualifiers.QualifierStringAnnotationLiteral;

//-Inject or programmatically lookup

//-In certain situations, injection is not the most convenient way to obtain a contextual reference. Fe, it may not be used when:
// • the concrete type of the objects to be injected may vary at runtime.
// • we would like to iterate over all beans of a certain type.
// • depending upon the deployment, there may be no bean which satisfies the type and qualifiers.
// • the objects require some custom initialization that is not performed by the bean constructor.
// • the objects to be injected are not required to be instances of beans.

//-Programmatic lookup allows to resolve a bean at runtime or find all beans that match a given type thanks to the Instance intf.
// 1. Instance injection points are always satisfied and never fail at deployment time
// 2. Instance provides test methods to know if requesting an instance is safe
// 3. With Instance you control when a bean instance is requested with the get() method

@WebServlet("/injectOrLookup")
public class InjectOrProgrammaticallyLookup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	Instance<Long> lg1;			//Injected
	Instance<Long> lg2;			//Looked up programmatically
	
	@Inject
	@QualifierString("stringProducer")
	Instance<String> str1;
	Instance<String> str2;
	
	@QualifierInt(142)			//This is not used, uses the one passed in select().
	Instance<String> str3;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<h3>Inject Or Lookup Programmatically</h3>");
		
		lg2 = CDI.current().select(Long.class, Default.Literal.INSTANCE);
		//lg2 = CDI.current().select(long.class);
		
		str2 = CDI.current().select(String.class, new QualifierStringAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			@Override
			public String value() {
				return "stringProducer";
			}
		});
		
		str3 = CDI.current().select(String.class, new QualifierStringAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			@Override
			public String value() {
				return "doubleQualifiersProducer";
			}
		});
		
		out.print("<br/>Long injected -> " + lg1.get());
		out.print("<br/>Long looked up -> " + lg2.get());
		
		out.print("<br/><br/>String injected -> " + str1.get());
		out.print("<br/>String looked up -> " + str2.get());
		
		out.print("<br/><br/>String looked up -> " + str3.get());
	}


}
