public class Test {
	public void printValue() {
		int n = 100;
		change(n);
		System.out.println(n);
	}
	public void change(int n ) {
		n += n;
	}
	public static void main(String[] args) {
		Test t = new Test();
		t.printValue();
	}
}
