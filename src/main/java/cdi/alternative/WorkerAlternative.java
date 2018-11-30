package cdi.alternative;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)				//To make @Alternative work without declaring it in beans.xml!
public class WorkerAlternative implements WorkerIntf {
//public class WorkerAlternative extends Worker {
	
	public String work() {
		return "WorkerAlternative works";
	}

}
