package literals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.util.TypeLiteral;

//-Supports inline instantiation of objects that represent parameterized types with actual type parameters.
//-An object that represents any parameterized type may be obtained by subclassing TypeLiteral.
// TypeLiteral<List<String>> stringListType = new TypeLiteral<List<String>>() { };

public class LiteralTypeSE {

	public LiteralTypeSE() {
		
		TypeLiteral<ArrayList<String>> typeLiteral = new TypeLiteral<ArrayList<String>>() {
			private static final long serialVersionUID = 1L;
		};
		
		Class<ArrayList<String>> rawType = typeLiteral.getRawType();
		Type type = typeLiteral.getType();
		String string = typeLiteral.toString();
		
		System.out.println("typeLiteral.getRawType() ->\t" + rawType);
		System.out.println("typeLiteral.getType() ->\t" + type);
		System.out.println("typeLiteral.toString() ->\t" + string);
		System.out.println();
		
		List<Type> types = Arrays.asList(Integer.class, String.class, rawType);
		System.out.println(types);
		
	}
	
	public static void main(String[] args) {
		new LiteralTypeSE();
	}
}
