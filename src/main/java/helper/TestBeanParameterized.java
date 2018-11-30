package helper;

public class TestBeanParameterized<T> {

	private T element;
	
	public TestBeanParameterized(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "TestBeanParameterized [element=" + element + "]";
	}
	
}
