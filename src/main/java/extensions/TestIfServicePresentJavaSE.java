package extensions;

import java.util.ServiceLoader;

import javax.enterprise.inject.spi.Extension;

//Need to remove '//' from META-INF/services/javax.enterprise.inject.spi.Extension
//There should be: extensions.example.SimpleTestProcessAnnotatedType@40e464
public class TestIfServicePresentJavaSE {

	public TestIfServicePresentJavaSE() {

		ServiceLoader<Extension> serviceLoader = ServiceLoader.load(Extension.class);
		
		System.out.println("Found: " + serviceLoader.spliterator().trySplit().estimateSize());
		
		for (Extension cpService : serviceLoader) {
			System.out.println(cpService);
		}
		
	}

	public static void main(String[] args) {
		new TestIfServicePresentJavaSE();
	}

}
