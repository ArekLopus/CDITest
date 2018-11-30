package configurators;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierString;

//-In CDI 2.0, it is possible to add Interceptor to a producer programmaticially.
//-An InterceptionFactory can be obtained be calling BeanManager.createInterceptionFactory().
//-The container must provide a built-in bean with scope @Dependent, bean type InterceptionFactory and qualifier @Default.
//-If an injection point of type InterceptionFactory and qualifier @Default exists and is
// not a parameter of a producer method, the container automatically detects the problem and treats it as a definition error.
//-If an injection point of type InterceptionFactory has a type parameter that is not a Java class, non-portable behavior results.
//-The following example demonstrates a producer method definition using InterceptionFactory.
// The produced bean instance will be a wrapper of Product with single interceptor associated by ActionBinding:
//	@Produces
//	@RequestScoped
//	public Product createInterceptedProduct(InterceptionFactory<Product> interceptionFactory) {
//	  interceptionFactory.configure().add(ActionBinding.Literal.INSTANCE);
//	  return interceptionFactory.createInterceptedInstance(new Product());
//	}

//-Only for @Produces
//-It adds the Interceptor to the RETURNING object!
//-InterceptionFactory allows to create a wrapper instance whose METHOD invocations
// are intercepted by method interceptors and forwarded to a provided instance.
//    TestBindingInterceptor's @AroundInvoke, method -> firstMethod]]
//	  TestBindingInterceptor's @AroundInvoke, method -> secondMethod]]
//-Must intercept a class!
// Exception thrown: WELD-001711: InterceptionFactory is not supported on interfaces. Check InterceptionFactory<helper.BeanIntf>]]

@WebServlet("/interceptionFactory")
public class InterceptionFactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("InterceptionFactory")
	TesterBean bean;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Producer Around Invoke Test.</h3>");
		
		out.println("Bean: " + bean);
		out.println("<br/>Method: " + bean.firstMethod());
		out.println("<br/>Method: " + bean.secondMethod());
		
	}
}
