package literals;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierStringAnnotationLiteral;
import qualifiers.QualifierWithNoMemebers;

//-An AnnotationLiteral represents an annotation as an abstract class and it is used mainly for manual injection

//-Works if qualifier is without any members
//	event.select(new AnnotationLiteral<QualifierWithNoMemebers>(){}).fire("Just Testing");

//-If qualifier has memebers, AnnotationLiteral must implement qualifier! 
//-The simplest way is to create an abstract class
//	public abstract class QualifierIntAnnotationLiteral extends AnnotationLiteral<QualifierInt> implements QualifierInt {}

//-Otherwise:
//	java.lang.RuntimeException: class abc.QualifiersServlet2$2
//	does not implement the annotation type with members qualifiers.QualifierInt
// event.select(new AnnotationLiteral<QualifierInt>(){
//	   public int value() {  return 2;  }
// }).fire("abc");		

@WebServlet("/annotationLiteral")
public class LiteralAnnotation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Instance<String> str1;
	Instance<String> str2;
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>AnnotationLiteral</h3>");
		out.println("<p>An AnnotationLiteral represents an annotation as an abstract class and it is used mainly for manual injection</p>");
		
		
		Annotation qs = new QualifierStringAnnotationLiteral() {
			@Override
			public String value() {
				return "stringProducer";
			}
		};
		
		str1 = CDI.current().select(String.class, qs);
		str2 = CDI.current().select(String.class, new AnnotationLiteral<QualifierWithNoMemebers>(){});
		
		out.println("String" + str1.get());
		out.println("<br/>String" + str2.get());
	}
}
