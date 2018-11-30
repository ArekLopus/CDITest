package cdi.interceptor_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-Binding annotation MUST be annotated with @Inherited and @InterceptorBinding.
//	@InterceptorBinding @Inherited public @interface TestBinding {}
//-Interceptor class MUST be annotated with the Binding annotation and the @Interceptor annotation.
//	@TestBinding and @Interceptor public class TestBindingInterceptor {}

//-In order for an interceptor to be invoked in a CDI app, it must, like an alternative, be specified in the beans.xml file. 
//	<interceptors>
//		<class>cdi.interceptor_binding.TestBindingInterceptor</class>
//	</interceptors>
//	OR
//-We can use @Priority annotation (on @Interceptor class) to enable the interceptor and define it’s ordering at the same time.	
//	@Priority(Interceptor.Priority.APPLICATION)

@WebServlet("/bindinginterceptor")
public class InterceptorBindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestBindingBean tb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@InterceptorBinding</h3>");
		out.println("Shortly: Create a Binding anottation with @InterceptorBinding, use It with @Interceptor class");
		out.println("<br/>Specify the Interceptor class in beans.xml or use @Priority and it is ready to use.");
		
		tb.methodCallTest();
		
		out.println();
		
	}
	
}
