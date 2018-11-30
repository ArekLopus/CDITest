package helper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Developer {
	
	@Inject
	Coffee coffee;
	
	public String develop() {
		return coffee.drink() + ", now time to work!";
	}
	
}
