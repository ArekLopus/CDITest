package injection.point;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import qualifiers.QualifierString;

@RequestScoped
public class TestCDI {
	
	@Inject
	@QualifierString("ip")
	private String thisIsNameOfTheField;
	
	
	public String test() {
		//System.out.println(thisIsName);
		return thisIsNameOfTheField;
	}
	
}
