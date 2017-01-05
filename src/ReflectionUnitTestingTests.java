import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.Test;

public class ReflectionUnitTestingTests {
	String className = "ReflectionAssert";
	String classExistsMethodName = "ClassExists";

	@Test
	public void ReflectionAssertClassShouldExist() {
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		assertNotNull(className + " class not found.", reflectionAssertClass);
	}

	public Class<?> getReflectionAssertClass() {
		try {
			return Class.forName("ReflectionUnitTesting." + className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@Test
	public void ClassExistsMethodShouldExistAndAcceptAString() {
		Method classExistsMethod = getClassExistsMethod();
		assertNotNull(classExistsMethodName + " method doesn't exist or doesn't accept a string.", classExistsMethod);

	}

	private Method getClassExistsMethod() {
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Class<?>[] parameterTypes = { String.class };
		try {
			return reflectionAssertClass.getMethod(classExistsMethodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}
}
