package concurency;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

//	@Singleton - application server manages concurrency.
//-By default it uses @ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) and @Lock(LockType.WRITE).
//-@ConcurrencyManagement and @Lock are only available on singleton session beans.

//-With ConcurrencyManagementType.BEAN - You are responsible for guarding its state against synchronization errors due to 
// concurrent access. In this case, you are allowed to use Java synchronization primitives such as synchronized and volatile.
// @Lock(LockType.WRITE) - DOES NOT WORK here!

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ConcurrentBeanEJB_Sin_BMC {

	public void testMethod() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
	@Lock(LockType.READ)
	public void testMethodWithLockRead() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
	@Lock(LockType.WRITE)
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
