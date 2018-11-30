package concurency;

import javax.ejb.Stateful;

//-@ConcurrencyManagement and @Lock are only available on singleton session beans.

//-By default, clients are allowed to make concurrent calls to a SF session object and the container is required to serialize
// such concurrent requests. Note that the container never permits multi-threaded access to the actual SF session bean instance.
//-For this reason, Read/Write method locking metadata, as well as the bean-managed concurrency mode,
// are not applicable to stateful session beans and must not be used.

@Stateful
public class ConcurrentBeanEJB_SF {

	public void testMethod() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
	//@Lock(LockType.READ)		//Container ->   @Lock is only permitted for singleton session beans]]
	public void testMethodWithLockRead() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
	//@Lock(LockType.WRITE)		//Container ->   @Lock is only permitted for singleton session beans]]
	public void testMethodWithLockWrite() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
	
	//For tests
	public String testMethod2() {
		return "Thread -> " + Thread.currentThread().getName() + ", Object -> " + this;
	}
}
