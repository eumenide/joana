package ifc;

import edu.kit.joana.ui.annotations.EntryPoint;
import edu.kit.joana.ui.annotations.EntryPointKind;
import edu.kit.joana.ui.annotations.Level;
import edu.kit.joana.ui.annotations.Sink;
import edu.kit.joana.ui.annotations.Source;

public class Main {

	static int x, y;
	
	@EntryPoint(kind = EntryPointKind.CONCURRENT)
	public static void main(String[] argv) throws InterruptedException {
		A a = new A();
		a.start();
//		a.join();
		y = inputPIN();
		while (y != 0)
			y--;
		x = 1;
		print(2);
//		new A().start();
	}

	static class A extends Thread {
		
		public void run() {
			Main.x = 0;
			Main.print(Main.x);
		}
	
	}
	
	@Source(level = Level.HIGH, lineNumber = 35, columnNumber = 1)
	public static int inputPIN() { return 42; }
	@Sink(level = Level.LOW, lineNumber = 37, columnNumber = 1)
	public static void print(int i) {}
	public static int input() { return 13; }

}