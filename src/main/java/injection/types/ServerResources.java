package injection.types;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerResources {
	
	@Resource(lookup="java:comp/DefaultManagedScheduledExecutorService")
	ManagedScheduledExecutorService mses;
	
	@Resource(lookup="java:comp/DefaultContextService")
	ContextService sc;
	
	@Resource(lookup="java:comp/DefaultManagedThreadFactory")
	ManagedThreadFactory mtf;
	@Resource
	ManagedThreadFactory mtf2;
	
	
	@Resource(lookup="java:comp/DefaultManagedExecutorService")
	ManagedExecutorService mes;
	@Resource
	ManagedExecutorService mes2;
	ManagedExecutorService mes3;
	
	@PostConstruct
	private void init() {
		try {
			mes3 = (ManagedExecutorService) InitialContext.doLookup("java:comp/DefaultManagedExecutorService");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void doSomething() {
		
		mes.execute(()->{
			System.out.println("Managed Executor Services / JNDI Name:	concurrent/__defaultManagedExecutorService");
			System.out.println("-----------------------------");
			System.out.println("ServerResources, ManagedExecutorService @Resource(lookup), thread: "+Thread.currentThread().getName());
		});
		
		mes2.execute(()->{
			System.out.println("-----------------------------");
			System.out.println("ServerResources, ManagedExecutorService @Resource, thread: "+Thread.currentThread().getName());
		});
		
		mes3.execute(()->{
			System.out.println("-----------------------------");
			System.out.println("ServerResources, ManagedExecutorService lookup, thread: "+Thread.currentThread().getName());
		});
		
		Thread th = mtf.newThread( () -> {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("-----------------------------");
			System.out.println("Managed Thread Factory / JNDI Name:	concurrent/__defaultManagedThreadFactory");
			System.out.println("ServerResources, ManagedThreadFactory @Resource(lookup), thread: "+Thread.currentThread().getName());
		});
		th.start();
		
		Thread th2 = mtf2.newThread( () -> {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("-----------------------------");
			System.out.println("ServerResources, ManagedThreadFactory @Resource, custom thread: "+Thread.currentThread().getName());
		});
		th2.setName("My thread");
		th2.setDaemon(true);
		th2.setPriority(Thread.MAX_PRIORITY);
		th2.start();
	}
}
