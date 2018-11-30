package concurency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@LockCDI
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LockCDIInterceptor {

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	@AroundInvoke
	public Object concurrencyControl(InvocationContext ctx) throws Exception {
		
		LockCDI lockAnnotation = ctx.getMethod().getAnnotation(LockCDI.class);

		if (lockAnnotation == null) {
			lockAnnotation = ctx.getTarget().getClass().getAnnotation(LockCDI.class);
		}

		Object returnValue = null;
		
		switch (lockAnnotation.value()) {
			
			case WRITE:
				ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
				try {
					writeLock.lock();
					returnValue = ctx.proceed();
				} finally {
					writeLock.unlock();
				}
				break;
		
			case READ:
				ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
				try {
					readLock.lock();
					returnValue = ctx.proceed();
				} finally {
					readLock.unlock();
				}
				break;
		}
		
		return returnValue;
	}
}