package events;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("eventScopeLifecycle")
public class EventseScopeLifecycleResource {
	
	@GET
	public String doGet() {
		return "Event Scope Lifecycle";
	}
}
