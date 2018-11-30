package literals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.TypeLiteral;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanParameterized;
import qualifiers.QualifierWithNoMemebers;

@WebServlet("/typeLiteral")
public class LiteralType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Instance<TestBeanParameterized<String>> pg1;
	Instance<TestBeanParameterized<String>> pg2;
	
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Parameterized Producer</h3>");
		
		
		TypeLiteral<TestBeanParameterized<String>> typeLiteral = new TypeLiteral<TestBeanParameterized<String>>() {};
		
		pg1 = CDI.current().select(typeLiteral);
		pg2 = CDI.current().select(typeLiteral, new AnnotationLiteral<QualifierWithNoMemebers>() {});
		
		
		out.println("<br/>ParameterizedGenerics1: " + pg1.stream().map(e -> e.getElement()).collect(Collectors.toList()));
		out.println("<br/>ParameterizedGenerics2: " + pg2.stream().map(e -> e.getElement()).collect(Collectors.toList()));
	}
}
