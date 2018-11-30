package events.async.v2_0;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletionStage;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qualifiers.QualifierInt;

//-A new firing method - fireAsync() and a corresponding observer - @ObservesAsync been added. 

@WebServlet("/eventAsync")
public class AnAsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
    ManagedExecutorService threadPool;
	
	@Inject
	@QualifierInt(95)
	Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Asynchronous Events</h3>");
		
		
		event.fireAsync("Fired: event.fireAsync()");
		
		CompletionStage<String> fa = event.fireAsync("Fired: event.fireAsync('info', NotificationOptions.ofExecutor(threadPool))",
				NotificationOptions.ofExecutor(threadPool));
		
		fa.thenAccept(this::eventDelivered);
		
	}
	
	
	void eventDelivered(String event) {
        System.out.println("Receipt after delivery -> "+ event + ", Thread -> " + Thread.currentThread().getName());
    }
}
