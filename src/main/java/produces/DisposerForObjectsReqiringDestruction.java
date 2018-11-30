package produces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierString;

//-Some producer methods return objects that require explicit destruction. Somebody needs to close a JDBC connection.
//	@Produces @RequestScoped Connection connect(User user) {
//	   return createConnection(user.getId(), user.getPassword());
//	}
//-Destruction can be performed by a matching disposer method, defined by the same class as the producer method:
//	void close(@Disposes Connection connection) {
//	   connection.close();
//	}
//-The disposer method must have at least one parameter, annotated @Disposes, with the same type and qualifiers as the producer method.
//-The disposer method is called automatically when the context ends (in this case, at the end of the request),
// and this parameter receives the object produced by the producer method.
// If the disposer method has additional method parameters, the container will look for a bean that satisfies
// the type and qualifiers of each parameter and pass it to the method automatically.

//-Since CDI 1.1 disposer methods may be used for destroying not only objects produced by producer methods
// but also objects producer by producer fields.


@WebServlet("/disposer")
public class DisposerForObjectsReqiringDestruction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("ComponentEnvironmentResources")
	EntityManager em;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Disposer method must have at least one parameter, annotated @Disposes, with the same type and qualifiers as the producer method.</h3>");
		
		out.println("EM Injected: " + em);
		
	}
	
//-From ProducersField.class
//	public void close(@Disposes @QualifierString("ComponentEnvironmentResources") EntityManager em) {
//		System.out.println("@Disposes @QualifierString('ComponentEnvironmentResources') called.");
//	    em.close();
//	}
	
}
