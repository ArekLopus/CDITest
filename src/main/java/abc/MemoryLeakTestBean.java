package abc;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

@Dependent
public class MemoryLeakTestBean {
	
	//private int[] arr = new int[100000];
	private int[] arr = new int[100];
	
	@PostConstruct
	public void init() {
		for(int i=0; i<arr.length; i++) {
			arr[i] = 255;
		}
	}
	
	
	public String callMe() {
		System.out.println("-------- TestBeanRequestScoped.callMe()");
		return "TestBeanApplicationScoped.getInfo() called";
	}

}
