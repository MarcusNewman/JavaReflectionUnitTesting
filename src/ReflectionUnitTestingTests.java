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
	public void MethodExistsMethodShouldExistAndAcceptAClassAStringAndAParameterArray() {
		Method methodExistsMethod = getMethodExistsMethod();
		assertNotNull(classExistsMethodName + " method doesn't exist or doesn't accept a Class, a string, and parameter types.", methodExistsMethod);

	}

	private Method getClassExistsMethod() {
		String methodName = classExistsMethodName;
		Class<?>[] parameterTypes = { String.class };
		return getMethodWithParameters(methodName, parameterTypes);
	}

	private Method getMethodExistsMethod() {
		String methodName = methodExistsMethodName;
		Class<?>[] parameterTypes = {Class.class, String.class, Class[].class};
		return getMethodWithParameters(methodName, parameterTypes);
	}

	private Method getMethodWithParameters(String className, Class<?>[] parameterTypes) {
		Class<?> reflectionAssertClass = getReflectionAssertClass();
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
	
	@Test(expected = AssertionFailedError.class)
	public void MethodExistsMethodShouldThrowAnAssertionFailedErrorWithAnInvalidMethodName() throws Throwable {
		thrown.reportMissingExceptionWithMessage(
				"MethodExists method should throw an assertionFailedError with an invalid class or method name.");
		String invalidMethodName = "InvalidMethodName";
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Method methodExistsMethod = getMethodExistsMethod();
		Object[] args = {reflectionAssertClass.getClass(), invalidMethodName, null };
		try {
			methodExistsMethod.invoke(reflectionAssertClass.newInstance(), args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	@Test
	public void MethodExistsMethodShouldNotThrowAnExceptionWithAValidClassMethodNameAndParameters() {
		Class<?> reflectionAssertClass = getReflectionAssertClass();
		Method methodExistsMethod = getMethodExistsMethod();
		Object[] args = {reflectionAssertClass.getClass(), classExistsMethodName, new Class<?>[]{String.class}};
		
			try {
				methodExistsMethod.invoke(reflectionAssertClass, args);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("MethodExists method should not throw an exception with a valid class, method name and parameters");
			}
//		} catch (InvocationTargetException e) {
//			
//		}
	}
}
