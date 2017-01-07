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
	 *            the name of the class, including package name.
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
	 * does not it throws an {@link AssertionError}.
	 * 
	 * @param methodName
	 */
	public void MethodExists(String methodName) {

	}
}
