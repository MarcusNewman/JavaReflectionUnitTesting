import static org.junit.Assert.*;
import java.lang.reflect.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.AssertionFailedError;

public class ReflectionUnitTestingTests {
	String className = "ReflectionAssert";
	String classExistsMethodName = "ClassExists";
	String methodExistsMethodName = "MethodExists";

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

	@Test
	public void MethodExistsMethodShouldExistAndAcceptAString() {
		Method methodExistsMethod = getMethodExistsMethod();
		assertNotNull(classExistsMethodName + " method doesn't exist or doesn't accept a string.", methodExistsMethod);

	}

	private Method getClassExistsMethod() {
		String methodName = classExistsMethodName;
		return getMethodWithStringParameter(methodName);
	}

	private Method getMethodExistsMethod() {
		String methodName = methodExistsMethodName;
		return getMethodWithStringParameter(methodName);
	}

	private Method getMethodWithStringParameter(String className) {

		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Class<?>[] parameterTypes = { String.class };
		try {
			return reflectionAssertClass.getMethod(className, parameterTypes);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test(expected = AssertionFailedError.class)
	public void ClassExistsMethodShouldThrowAnAssertionFailedErrorWithAnInvalidClassName() throws Throwable {
		thrown.reportMissingExceptionWithMessage(
				"ClassExists method should throw an assertionFailedError with an invalid class name.");
		String invalidClassName = "InvalidClassName";
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Method classExistsMethod = getClassExistsMethod();
		Object[] args = { invalidClassName };
		try {
			classExistsMethod.invoke(reflectionAssertClass.newInstance(), args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	@Test
	public void ClassExistsMethodShouldNotThrowAnExceptionWithAValidClassName() throws Throwable {
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Method classExistsMethod = getClassExistsMethod();
		Object[] args = { "ReflectionUnitTesting." + className };
		try {
			classExistsMethod.invoke(reflectionAssertClass.newInstance(), args);
		} catch (InvocationTargetException e) {
			fail("ClassExists method should not throw an exception with a valid class name.");
		}
	}
}
