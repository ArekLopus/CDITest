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

//-CDI Bean qualifiers CAN NOT BE combined, for example
//	@Inject
//	@QualifierString 
//	Instance<BeanIntf> bean;
//	bean = CDI.current().select(BeanIntf.class, new QualifierIntAnnotationLiteral() {...});
//-@Inject and @QualifierString  are ignored. Only select(@QualifierInt) counts. 

//Beans:
//-A Bean is injected if the set of qualifiers at Injection Point is a subset of the Beans’s qualifiers!
// But not if there are no qualifiers

// • 0 qualifiers - Only bean with no qualifier
// • 1 qualifier - all beans with this qualifier (also if it is a subset)
// • 2 qualifiers - bean with both

@WebServlet("/qualifiersBean")
public class AQualifiersServletBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject							//If no qualifier - only bean with no qualifier
	Instance<BeanIntf> bean1;		//Bean1, no qualifier[qualifiers.info.BeanAnn_0_@29d178] 
	
	@Inject
	@QualifierString				//@QualifierString - all beans with @QualifierString (also if it is a subset)
	Instance<BeanIntf> bean2;		//Bean2: [qualifiers.info.BeanAnn_2_@1b82d29, qualifiers.info.BeanAnn_1s_@5ae3c6] 
	
	@Inject
	@QualifierInt					//@QualifierInt - all beans with @QualifierInt (also if it is a subset)
	Instance<BeanIntf> bean3;		//Bean3: [qualifiers.info.BeanAnn_2_@9180c0, qualifiers.info.BeanAnn_1i_@c926fb] 
	
	@Inject
	@QualifierInt
	@QualifierString				//2 qualifiers - all beans with both
	Instance<BeanIntf> bean4;		//Bean4: [qualifiers.info.BeanAnn_2_@8498ae] 
	
	
	
	@Inject							//@Inject and @QualifierString are omitted.
	@QualifierString				//CDI.current().select(@QualifierInt) takes over. (It is like bean3 with @QualifierInt only) 
	Instance<BeanIntf> bean5;		//Bean5: [qualifiers.info.BeanAnn_2_@1d1162b, qualifiers.info.BeanAnn_1i_@1601ad1] 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Qualifiers used with qualified CDI Beans</h3>");
		
		Annotation qi = new QualifierIntAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			public int value() {
				return 0;
			}
		};
		
		bean5 = CDI.current().select(BeanIntf.class, qi);
		
		
		out.println("If no qualifier - only bean with no qualifier");
		out.println("<br/>Bean1, no qualifier" + bean1.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@QualifierString - all beans with @QualifierString (also if it is a subset)");
		out.println("<br/>Bean2: " + bean2.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@QualifierInt - all beans with @QualifierInt (also if it is a subset)");
		out.println("<br/>Bean3: " + bean3.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>2 qualifiers - all beans with both");
		out.println("<br/>Bean4: " + bean4.stream().collect(Collectors.toList()));
		
		out.println("<br/><br/>@Inject and @QualifierString are omitted, CDI.current().select(@QualifierInt) takes over. (It is like bean3 with @QualifierInt only)");
		out.println("<br/>Bean5: " + bean5.stream().collect(Collectors.toList()));
		
	}
}
