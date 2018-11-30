package cdi_2_se;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import cdi.alternative.WorkerIntf;

//		Does NOT NEED anything more than org.jboss.weld.se from POM
//-bean.xml in META-INF folder must be provided, otherwise:
// Exception in thread "main" java.lang.IllegalStateException: WELD-ENV-000016: Missing beans.xml file in META-INF
public class AJavaSETestAlternative {
	

	public static void main(String... args) {
		
		try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			
			System.out.println("Is container running: "+container.isRunning());
			
			Instance<WorkerIntf> worker = container.select(WorkerIntf.class);
			WorkerIntf wi = worker.get();
			
			System.out.println(wi.work());
			
			
    }
    //Shuts down automatically after the try with resources block.
}
	
}
