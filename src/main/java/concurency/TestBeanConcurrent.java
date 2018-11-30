package concurency;

import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestBeanConcurrent {
	
	public void testMethod() {
		try {
			Thread.sleep(2000);
			System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void testMethodSynchronized() {
		try {
			Thread.sleep(2000);
			System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@LockCDI(LockType.WRITE)
	public void testMethodLocked() {
		try {
			Thread.sleep(2000);
			System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//For tests
	public String testMethod2() {
		return "Thread -> " + Thread.currentThread().getName() + ", Object -> " + this;
	}
	
	synchronized public String testMethodSynchronized2() {
		return "Thread -> " + Thread.currentThread().getName() + ", Object -> " + this;
	}
	
	@LockCDI(LockType.WRITE)
	public String testMethodLocked2() {
		return "Therad -> " + Thread.currentThread().getName() + ", Object -> " + this;
	}

}
