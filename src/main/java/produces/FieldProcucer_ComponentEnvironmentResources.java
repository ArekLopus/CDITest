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

//-A producer field is really just a shortcut that lets us avoid writing a useless getter method.
// However, in addition to convenience, producer fields serve a specific purpose as an adaptor for Java EE component env injection.

//-The CDI spec uses the term resource to refer, generically, to any of object which might be available in the Java EE component env:
//  • JDBC `Datasource`s, JMS `Queue`s, `Topic`s and `ConnectionFactory`s, JavaMail `Session`s and other transactional resources (JCA),
//  • JPA `EntityManager`s and `EntityManagerFactory`s,
//  • remote EJBs, and
//  • web services.

//-We declare a resource by annotating a producer field with a component environment injection annotation:
// @Resource, @EJB, @PersistenceContext, @PersistenceUnit or @WebServiceRef.
//-The field may be static (but not final).

//-A resource declaration really contains two pieces of information:
//  • the JNDI name, EJB link, persistence unit name, or other metadata needed to obtain a reference to the resource from the comp env
//  • the type and qualifiers that we will use to inject the reference into our beans.

@WebServlet("/producesFieldResources")
public class FieldProcucer_ComponentEnvironmentResources extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("ComponentEnvironmentResources")
	EntityManager em;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Producer fields also serve a specific purpose as an adaptor for Java EE component environment injection.</h3>");
		
		out.println("EM Injected: " + em);
		
	}
	

	
}
