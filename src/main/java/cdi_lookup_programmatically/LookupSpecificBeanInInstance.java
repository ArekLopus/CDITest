package cdi_lookup_programmatically;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.BeanIntf;
import helper.BeanTwo;

//-@Inject injects all beans that matches.
//-We look up for a specific bean among injected ones.

@WebServlet("/iterateBeans")
public class LookupSpecificBeanInInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BeanIntf thisIsBeanWeAreLookingFor;
	
	@Inject
	Instance<BeanIntf> abc;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Iterate Beans at Runtime.</h3>");
		
		
		Iterator<BeanIntf> iterator = abc.iterator();
		while(iterator.hasNext()) {
			BeanIntf next = iterator.next();
			out.println("<br/>Found bean: " + next);
			if(next instanceof BeanTwo) {
				thisIsBeanWeAreLookingFor = next;
			}
		}
		
		if(thisIsBeanWeAreLookingFor != null) {
			out.println("<br/><br/>BeanTwo instance found: " + thisIsBeanWeAreLookingFor.justTest());
		} else {
			out.println("<br/><br/>BeanTwo instance NOT found!");
		}

	}
	
}
