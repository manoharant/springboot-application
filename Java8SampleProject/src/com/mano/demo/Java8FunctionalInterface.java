package com.mano.demo;

public class Java8FunctionalInterface {
	public static void main(String[] args) {

		MathOperation addOperation = (a, b) -> a + b;
		MathOperation subOperation = (a, b) -> a - b;
		MathOperation multiOperation = (a, b) -> a * b;
		MathOperation divideOerration = (a, b) -> a / b;

		System.out.println("Addition::" + operate(10, 10, addOperation));
		System.out.println("Sub::" + operate(10, 10, subOperation));
		System.out.println("Multiplication::" + operate(10, 10, multiOperation));
		System.out.println("Division::" + operate(10, 10, divideOerration));

		// Java 7 way of running thread..
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++)
					System.out.println("JAva 7 Run method :: " + i);
			}
		});
		t.start();
		// Java 8 way of running thread..

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++)
				System.out.println("Java 8 Run Method ::" + i);
		});
		t1.start();
	}

	static int operate(int a, int b, MathOperation operation) {
		return operation.operation(a, b);
	}
}

@FunctionalInterface
interface MathOperation {
	int operation(int a, int b);
}