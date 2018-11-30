package extensions.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Vetoed;

@Vetoed		//Otherwise AmbiguousResolutionException (This one is a bean and bean created by extension)
public class IntegrateMyBean {
	
	private String info = "Default Info from IntegrateMyBean";
	
	public IntegrateMyBean() {
		System.out.println("IntegrateMyBean's constructor");
	}
	
	@PostConstruct
	private void init() {
		System.out.println("IntegrateMyBean's @PostConstruct");
	}
	
	@PreDestroy
	private void destroy() {
		System.out.println("IntegrateMyBean's @PreDestroy");
	}

	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
