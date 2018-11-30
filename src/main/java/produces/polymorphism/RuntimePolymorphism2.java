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

import helper.PaymentIntf;
import produces.producers.ProducersPolymorphism;
import qualifiers.QualifierString;
import qualifiers.Payment.PaymentType;

//Like producesPolymorphism1, but beans are auto injected in @Produce method.

//http://localhost:8080/CDITest/producesPolymorphism2?payment=visa
//http://localhost:8080/CDITest/producesPolymorphism2?payment=master
//http://localhost:8080/CDITest/producesPolymorphism2?payment=wire
@WebServlet("/producesPolymorphism2")
public class RuntimePolymorphism2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("paymentProducer2")
	Instance<PaymentIntf> payment;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concrete type of the objects to be injected may vary at runtime.</h3>");
		
		ProducersPolymorphism.payment = PaymentType.NONE;
		
		String param = request.getParameter("payment");
		if(param == null) {
			out.println("Pick your payment: ?payment=visa, ?payment=master, ?payment=wire");
			return;
		}
		System.out.println(param);
		switch (param) {
    		case "visa" : ProducersPolymorphism.payment = PaymentType.VISA; break;
    		case "master" : ProducersPolymorphism.payment = PaymentType.MASTERCARD; break;
    		case "wire" : ProducersPolymorphism.payment = PaymentType.WIRE_TRANSFER; break;
    		default: ProducersPolymorphism.payment = PaymentType.NONE;
		}
		
		System.out.println(ProducersPolymorphism.payment);
		out.println("Payment method: " + (payment.get() == null ? "None" : payment.get().transfer()));
		
		
		out.println("<br/>");
	}
	
}

