package ReflectionUnitTesting;

import junit.framework.AssertionFailedError;

/**
 * Class to test Classes and Methods using reflection.
 * 
 * @author Marcus Newman
 *
 */
public class ReflectionAssert {
	/**
	 * Method that tests for the existence of a Class using reflection. If it
	 * doesn't it throws an {@link AssertionFailedError}.
	 *
	 * @param className
	 *            The name of the class, including package name.
	 * @throws AssertionFailedError
	 */
	public void ClassExists(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new AssertionFailedError();
		}
	}

	/**
	 * Method that tests for the existence of a Method using reflection. If it
	 * doesn't exist it throws an {@link AssertionFailedError}.
	 * 
	 * @param classType
	 *            The type of class containing the method to check.
	 * @param methodName
	 *            The name of the method to check.
	 * @param args
	 *            The types of parameters expected on the method.
	 * @throws AssertionFailedError
	 */
	public void MethodExists(Class<?> classType, String methodName, Class<?>... args) {
		try {
			classType.getMethod(methodName, args);
		} catch (NoSuchMethodException e) {
			throw new AssertionFailedError();
		}
	}
}
