package jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.specializes.Greeter;

@Path("specialized")
public class SpecializesResource {
	
	@Inject
	Greeter gr;
	
	@GET
	public String specializedTest() {
		return "Greeter: " + gr.greet();
	}

}
