package produces.polymorphism;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.PaymentIntf;
import qualifiers.Payment.PaymentType;
import qualifiers.PaymentAnnotationLiteral;

//-Objects have qualifiers. A proper object is injected when we select() with qualifier.

//http://localhost:8080/CDITest/producesPolymorphism3?payment=visa
//http://localhost:8080/CDITest/producesPolymorphism3?payment=master
//http://localhost:8080/CDITest/producesPolymorphism3?payment=wire
@WebServlet("/producesPolymorphism3")
public class RuntimePolymorphism3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Instance<PaymentIntf> payment;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concrete type of the objects to be injected may vary at runtime.</h3>");
		
		String param = request.getParameter("payment");
		if(param == null) {
			out.println("Pick your payment: ?payment=visa, ?payment=master, ?payment=wire");
			return;
		}
		
		PaymentType type = PaymentType.NONE;
		
		switch (param) {
    		case "visa" : type = PaymentType.VISA; break;
    		case "master" : type = PaymentType.MASTERCARD; break;
    		case "wire" : type = PaymentType.WIRE_TRANSFER; break;
    		default: type = PaymentType.NONE;
		}
		
		setPaymentType(type);
		
		out.println("Payment method: " + (payment.get() == null ? "None" : payment.get().transfer()));
		
	}
	
	private void setPaymentType(PaymentType pt) {
		payment = CDI.current().select(PaymentIntf.class, new PaymentAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			@Override
			public PaymentType value() {
				return pt;
			}
		});
	}
}

