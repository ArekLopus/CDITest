package generics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanParameterized;
import qualifiers.QualifierWithNoMemebers;

@WebServlet("/parameterizedProducer")
public class ParameterizedProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Instance<TestBeanParameterized<?>> pg1;
	
	@Inject
	Instance<TestBeanParameterized<String>> pg2;
	
	Instance<TestBeanParameterized<String>> pg3;
	Instance<TestBeanParameterized<String>> pg4;
	
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Parameterized Producer</h3>");
		
		
		TypeLiteral<TestBeanParameterized<String>> typeLiteral = new TypeLiteral<TestBeanParameterized<String>>() {
			private static final long serialVersionUID = 1L;
		};
		
		pg3 = CDI.current().select(typeLiteral);
		pg4 = CDI.current().select(typeLiteral, new AnnotationLiteral<QualifierWithNoMemebers>() {});
		
		
		out.println("ParameterizedGenerics1: " + pg1.stream().map(e -> e.getElement()).collect(Collectors.toList()));
		out.println("<br/>ParameterizedGenerics2: " + pg2.stream().map(e -> e.getElement()).collect(Collectors.toList()));
		out.println("<br/>ParameterizedGenerics3: " + pg3.stream().map(e -> e.getElement()).collect(Collectors.toList()));
		out.println("<br/>ParameterizedGenerics4: " + pg4.stream().map(e -> e.getElement()).collect(Collectors.toList()));
	}
}
