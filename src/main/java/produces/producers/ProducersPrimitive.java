package produces.producers;

import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.inject.Produces;

import qualifiers.QualifierString;

public class ProducersPrimitive {
	
	public static int seed = 1;
	
	
	@Produces
	@QualifierString("randomizer")
	public int produceRandomNumber() {
		int nextInt = ThreadLocalRandom.current().nextInt(ProducersPrimitive.seed) + 1;
		return nextInt;
	}
	
	
	@Produces
	@QualifierString("primitiveArray")
	public int[] produceArray() {
		seed = 100;
		int[] arr = new int[10];
		for(int i = 0; i<10; i++) {
			arr[i] = (ThreadLocalRandom.current().nextInt(ProducersPrimitive.seed) + 1);
		}
		seed=1;
		return arr;
	}
	
}
