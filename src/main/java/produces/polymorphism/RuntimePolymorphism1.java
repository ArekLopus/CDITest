package produces.polymorphism;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.BeanIntf;
import produces.producers.ProducersPolymorphism;
import qualifiers.QualifierString;

//-Producer methods let us use runtime polymorphism with CDI.
//-Concrete type of the objects to be injected may vary at runtime.

//http://localhost:8080/CDITest/producesPolymorphism1?bean=one
//http://localhost:8080/CDITest/producesPolymorphism1?bean=two
//http://localhost:8080/CDITest/producesPolymorphism1?bean=three

//-We set the bean property and @Produces method returns a proper object using this property.
@WebServlet("/producesPolymorphism1")
public class RuntimePolymorphism1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("paymentProducer1")
	Instance<BeanIntf> payment;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concrete type of the objects to be injected may vary at runtime.</h3>");
		
		ProducersPolymorphism.bean = "none";
		
		String param = request.getParameter("bean");
		if(param == null) {
			out.println("Pick your payment: ?bean=one, ?bean=two, ?bean=three");
			return;
		}
		ProducersPolymorphism.bean = param;
		BeanIntf bean = payment.get();
		
		out.println("Bean: " + (bean == null ? "None" : bean.justTest()));

	}
	
}

