package cdi.typed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-@Typed is kind of @Qualifier
//-@Typed lists the classes that should be bean types.
//-Fe, the bean types of this bean have been restricted to Shop<Book>, together with java.lang.Object:
//	@Typed(Shop.class)    public class BookShop extends Business implements Shop<Book> {}
//-The unrestricted set of bean types for a managed bean contains
// the bean class, every superclass and all interfaces it implements directly or indirectly.

//Without @Typed
//org.jboss.weld.exceptions.DeploymentException: WELD-001409: Ambiguous dependencies for type ClassOne with qualifiers @Default
//at injection point [BackedAnnotatedField] @Inject cdi.typed.ClassBean.cl
//at cdi.typed.ClassBean.cl(ClassBean.java:0)
//Possible dependencies: 
//- Managed Bean [class cdi.typed.ClassOne] with qualifiers [@Any @Default],
//- Managed Bean [class cdi.typed.ClassTwo] with qualifiers [@Any @Default]

@WebServlet("/typed")
public class ATypedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	ClassOne cl;
	
	@Inject
	ClassTwo cl2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Typed lists the classes that should be bean types.</h3>");
		out.println("The unrestricted set of bean types for a managed bean contains:");
		out.println("<br/>the bean class, every superclass and all interfaces it implements directly or indirectly."); 
		
		out.println("<br/><br/>Here ClassTwo extends ClassOne, so it has @Typed(ClassTwo.class) to avoid ambiguous dependencies.");
		out.println("<br/>ClassOne: " + cl.classTest() + "<br/>ClassTwo: " + cl2.classTest());
	}
	
}
