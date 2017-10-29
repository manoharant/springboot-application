package com.mano.demo;

public class Java8DefaultStaticMethodExample {
	public static void main(String[] args) {
		new MyClass().method1();
		new MyClass().method2();
		new MyClass().defaultMethod();
		new MyClass().staticMethod();
	}
}

interface Interface1 {
	void method1();

	default void defaultMethod() {
		System.out.println("Interface1 default method..");
	}

	static void staticMethod() {
		System.out.println("Interface1 static method ..");
	}
}

interface Interface2 {
	void method2();

	default void defaultMethod() {
		System.out.println("Interface2 default method..");
	}

	static void staticMethod() {
		System.out.println("Interface2 static methos..");
	}
}

class MyClass implements Interface1, Interface2 {

	@Override
	public void method2() {
		System.out.println("MyClass Method2");
	}

	@Override
	public void method1() {
		System.out.println("MyClass Method1");
	}

	public void defaultMethod() {
		System.out.println("MyClass defaultMethod overridden");
	}

	public void staticMethod() {
		Interface1.staticMethod();
		Interface2.staticMethod();
	}
}