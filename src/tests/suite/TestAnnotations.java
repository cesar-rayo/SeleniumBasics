package tests.suite;

import org.testng.annotations.Test;

public class TestAnnotations {
	
	@Test(dependsOnMethods={"c","d"},groups={"A"})
	public void a() {
	}
	
	@Test(dependsOnMethods={"a"},groups={"A"})
	public void b() {
	}
	
	@Test(groups={"B"})
	public void c() {
	}
	
	@Test(groups={"C"})
	public void d() {
	}
	
	@Test(groups={"C"})
	public void e() {
	}
	
	@Test(enabled=false,groups={"C"})
	public void f() {
	}
}
