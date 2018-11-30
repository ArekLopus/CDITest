package produces.polymorphism;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.BeanIntf;
import helper.PaymentIntf;
import qualifiers.Payment;
import qualifiers.Payment.PaymentType;

//-Producer methods let us use runtime polymorphism with CDI.
//-Concrete type of the objects to be injected may vary at runtime.

@WebServlet("/producesPolymorphism")
public class RuntimePolymorphism extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Instance<BeanIntf> beans;			//No qualifier annotation, injected all 3
	
	@Inject
	@Any
	Instance<PaymentIntf> payments;		//Qualifier annotation on beans, without @Any injected 0
	
	@Inject
	@Payment(PaymentType.VISA)
	Instance<PaymentIntf> payment;		//Qualifier annotation on beans, injected 0
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concrete type of the objects to be injected may vary at runtime.</h3>");

		//beans = javax.enterprise.inject.spi.CDI.current().select(BeanIntf.class);
		long count1 = beans.stream().count();
		
		out.println("All Beans: " + count1);
		out.println("<br/> " + beans.stream().collect(Collectors.toList()));
		out.println("<br/>");
		
		//payments = javax.enterprise.inject.spi.	CDI.current().select(PaymentIntf.class, Any.Literal.INSTANCE);
		long count2 = payments.stream().count();
		out.println("<br/>All Payments: " + count2);
		out.println("<br/> " + payments.stream().collect(Collectors.toList()));
		out.println("<br/>");
		
		long count3 = payment.stream().count();
		out.println("<br/>Visa Payment: " + count3);
		out.println("<br/> " + payment.stream().collect(Collectors.toList()));
		out.println("<br/>");
		
		out.println("<br/>");
	}
	
}

