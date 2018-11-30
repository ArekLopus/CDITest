package qualifiers.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;
import qualifiers.QualifierIntAnnotationLiteral;
import qualifiers.QualifierString;

//-Qualifiers can be applied in one of two ways:
// • by annotating the injection point, or
// • by passing qualifiers to the select().

//-Producer method qualifiers CAN NOT BE combined, for example
//	@Inject 
//	@QualifierString 
//	Instance<StringBuilder> sb;
//	sb = CDI.current().select(StringBuilder.class, new QualifierIntAnnotationLiteral() {...});
//-@Inject and @QualifierString  are ignored. Only select(@QualifierInt) counts. 

//Producers:
//-A producer method is called if the set of qualifiers at Injection Point is a subset of the method’s qualifiers!
// But not if there are no qualifiers

// • 0 qualifiers - Only producer method with no qualifier
// • 1 qualifier - producer methods with this qualifier (also if it is a subset)
// • 2 qualifiers - producer method with both

@WebServlet("/qualifiersProducer")
public class AQualifiersServletProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	@Inject							//If no qualifier - StringBuilder with no qualifiers 
	Instance<StringBuilder> sb1;	//StringBuilder1, no qualifier[StringBuilder with no Qualifiers] 
	
	@Inject
	@QualifierString				//@QualifierString - StringBuilder with @QualifierString (also if it is a subset) 
	Instance<StringBuilder> sb2;	//StringBuilder2: [StringBuilder with 1s Qualifier, StringBuilder with 2 Qualifiers] 
	
	@Inject
	@QualifierInt					//@QualifierInt - StringBuilder with @QualifierInt (also if it is a subset)  
	Instance<StringBuilder> sb3;	//StringBuilder3: [StringBuilder with 1i Qualifier, StringBuilder with 2 Qualifiers] 
	
	@Inject
	@QualifierInt
	@QualifierString				//2 qualifiers - StringBuilder with both 
	Instance<StringBuilder> sb4;	//StringBuilder4: [StringBuilder with 2 Qualifiers] 
	
	
	
	@Inject							//@Inject and @QualifierString are omitted.
	@QualifierString				//CDI.current().select(@QualifierInt) takes over. (Like StringBuilder3 with @QualifierInt only) 
	Instance<StringBuilder> sb5;	//StringBuilder5: [StringBuilder with 1i Qualifier, StringBuilder with 2 Qualifiers]
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Producer methods with Qualifiers</h3>");
		
		Annotation qi = new QualifierIntAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			public int value() {
				return 0;
			}
		};
		
		sb5 = CDI.current().select(StringBuilder.class, qi);
		
		
		out.println("If no qualifier - StringBuilder with no qualifiers");
		out.println("<br/>StringBuilder1, no qualifier" + sb1.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@QualifierString - StringBuilder with @QualifierString (also if it is a subset)");
		out.println("<br/>StringBuilder2: " + sb2.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@QualifierInt - StringBuilder with @QualifierInt (also if it is a subset) ");
		out.println("<br/>StringBuilder3: " + sb3.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>2 qualifiers - StringBuilder with both ");
		out.println("<br/>StringBuilder4: " + sb4.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@Inject and @QualifierString are omitted, CDI.current().select(@QualifierInt) takes over. (It is like StringBuilder3 with @QualifierInt only)");
		out.println("<br/>StringBuilder5: " + sb5.stream().collect(Collectors.toList()));
		
	}
}
