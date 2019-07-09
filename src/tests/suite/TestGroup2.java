package tests.suite;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestGroup2 {
	@Test(dependsOnMethods={"f4","f5"},groups={"A"},alwaysRun=true)
	public void f1() {
	}
	
	@Test(dependsOnMethods={"f1"},groups={"A"})
	public void f2() {
	}
	
	@Test(groups={"A"})
	@Parameters("testName")
	public void f3(String testName) {
		System.out.println("Parameter: " + testName);
	}
	
	@Test(groups={"B"})
	@Parameters({"param_1","param_2"})
	public void f4(String param_1, String param_2) {
		System.out.println("Parameter_1: " + param_1);
		System.out.println("Parameter_2: " + param_2);
	}
	
	@Test(groups={"B"})
	public void f5() {
		Assert.assertEquals(is_equal(10, 11), true); //finishes method execution if fails
		System.out.println(">> Next instruction");
	}
	
	@Test(enabled=false,groups={"C"})
	public void f6() {
	}
	
	@Test(groups={"C"})
	public void f7() {
		Assert.assertEquals(is_equal(10, 10), true); //finishes method execution if fails
		System.out.println(">> Next instruction");
	}
	
	@Test(expectedExceptions= ArithmeticException.class)
	public void f8() {
		//Negative test
		float some = 5/0;
		System.out.println(">> Next instruction");
	}
	
	public boolean is_equal(int a, int b){
		if(a == b){
			return true;
		}else{
			return false;
		}
	}
}
