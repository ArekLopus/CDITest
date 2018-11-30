package produces.polymorphism;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.BeanIntf;
import qualifiers.QualifierBeanAnnotationLiteral;

//-@Producer method checks injection point qualifier and produces a proper bean.
// Using qualifier @ann with type memeber, or select() with qualifier.

//http://localhost:8080/CDITest/producesPolymorphism4?type=one
//http://localhost:8080/CDITest/producesPolymorphism4?type=two
//http://localhost:8080/CDITest/producesPolymorphism4?type=three
@WebServlet("/producesPolymorphism4")
public class RuntimePolymorphism4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	//@QualiferBean(value="polymorphismTest", type="two")
	Instance<BeanIntf> bean;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concrete type of the objects to be injected may vary at runtime.</h3>");
		
		String param = request.getParameter("type");
		if(param == null) {
			out.println("Pick your payment: ?type=one, ?type=two, ?type=three");
			return;
		}
		
		setBeanType(param);
		
		long count1 = bean.stream().count();
		
		out.println("All Beans: " + count1);
		out.println("<br/> " + bean.stream().collect(Collectors.toList()));
		out.println("<br/>");
		
		
		out.println("<br/>");
	}
	
	private void setBeanType(String type) {
		bean = javax.enterprise.inject.spi.CDI.current().select(BeanIntf.class , new QualifierBeanAnnotationLiteral () {
			private static final long serialVersionUID = 1L;

			@Override
			public String value() {
				return "polymorphismTest";
			}

			@Override
			public String type() {
				return type;
			}
		});
	}
}

