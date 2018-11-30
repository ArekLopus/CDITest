package jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.alternative.WorkerIntf;

@Path("alternative")
public class AlternativeResource {
	
	@Inject
	WorkerIntf wr;
	
	@GET
	public String alternativeTest() {
		return "Worker: " + wr.work();
	}

}
