package jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.stereotype.MyStereotype;

@MyStereotype
@Path("stereotype")
@RequestScoped
public class StereotypeResource {
	
	
	@GET
	public String stereotypeTest() {
		System.out.println("StereotypeResource's stereotypeTest() called.");
		return "StereotypeResource ";
	}

}
