package cdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JustABean;
import helper.TestBeanApplicationScoped;
import helper.TestBeanSessionScoped;

//https://javaee.github.io/javaee-spec/javadocs/javax/enterprise/inject/spi/BeanManager.html
//https://docs.jboss.org/weld/reference/latest/en-US/html/extend.html#_the_literal_beanmanager_literal_object

//-Allows a portable extension to interact directly with the container.
// Provides operations for obtaining contextual referencesfor beans, along with many other operations of use to portable extensions. 

//-Any bean may obtain an instance of BeanManager by injecting it: 
//	@Inject
//	BeanManager manager;
//or
//	CDI.current().getBeanManager();

//-Java EE components may obtain an instance of BeanManager from JNDI by looking up the name java:comp/BeanManager. 
//-Most operations of BeanManager may be called at any time during the execution of the application. 

@WebServlet("/beanManager")
public class BeanManagerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	BeanManager beanManager1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Accessing and Using BeanManager</h3>");
		
		BeanManager beanManager2 = CDI.current().getBeanManager();
		
		try {
			
			BeanManager beanManager3 = InitialContext.doLookup("java:comp/BeanManager");
			
			search(beanManager1, out, TestBeanApplicationScoped.class, Any.Literal.INSTANCE);
			search(beanManager2, out, TestBeanSessionScoped.class, Any.Literal.INSTANCE);
			search(beanManager3, out, JustABean.class, Any.Literal.INSTANCE);
			
		} catch (NamingException e) {
			out.println("<br/>Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
	private void search(BeanManager bm, PrintWriter out, Type beanType, Annotation... qualifiers) {
		Set<Bean<?>> beans = bm.getBeans(beanType, qualifiers);
		out.println("<br/><br/><br/>Type: " + beanType.getTypeName());
		out.println("<br/>Beans: " + beans);
		Bean<?> bean = beans.iterator().next();
		
		out.println("<br/><br/>First Bean: " + bean);
		out.println("<br/>First Bean's Scope: " + bean.getScope() + "<br/>");
	}
}
