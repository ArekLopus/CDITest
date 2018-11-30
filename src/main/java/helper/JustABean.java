package helper;

import java.io.Serializable;

public class JustABean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String info = "";
	
	public JustABean() {}
	
	public JustABean(String info) {
		this.info = info;
	}
	
	public String testMethod() {
		return "JustABean.testMethod() called, info: " + this.info;
	}
	
}
