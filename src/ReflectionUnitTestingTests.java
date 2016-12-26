import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectionUnitTestingTests {
	String className = "ReflectionAssert";
	
	@Test
	public void ReflectionAssertClassShouldExist() {		
		Class reflectionAssertClass = GetReflectionAssertClass();
		assertNotNull(className + " class not found.", reflectionAssertClass);		
	}
	
	public Class GetReflectionAssertClass(){
		try {
			return Class.forName("ReflectionUnitTesting." + className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	@Test
	public void ClassShouldExistMethodShouldExist(){
		String classExistsMethodName = "ClassExists";
		Class reflectionAssertClass = GetReflectionAssertClass();
		try{
			reflectionAssertClass.getMethod(classExistsMethodName, null);
		}
		catch(NoSuchMethodException e){
			fail(classExistsMethodName + " method doesn't exist.");
		}
		
	}

}
