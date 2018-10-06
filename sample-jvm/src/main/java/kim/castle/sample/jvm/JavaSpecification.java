package kim.castle.sample.jvm;

public class JavaSpecification {

	private int a = 0xCad;
	private float b = 0x1.fffffeP+127f;
	private float c = 1996;
	// private float d = 1996.3;
	// private int f = 9999e2;
	private double g = 20e2;
	private float h = 0x2p-3f;
	// private float i=1.fffep-12f;
	private long p = 0b1_0;
	// private long q=0b1_1_1_0_2;
	private long r = 2_147_483_648l;

	public static void main(String[] args) {
		JavaSpecification o = new JavaSpecification();
		o.print();
		o.printInt(-1);
		
		System.out.println(0x80000000>>>1);
	}

	private void print() {
		System.out.println("a=" + a);
		System.out.println("b=" + b);
		System.out.println("c=" + c);
		System.out.println("g=" + g);
		System.out.println("h=" + h);
		System.out.println("p=" + p);
		System.out.println("r=" + r);
	}
	
	private void printInt(int input) {
		for(int i=0;i<32;i++) {
			int t = (input & 0x80000000>>>i)>>>(31-i);
			System.out.print(t);
		}
		System.out.println();
	}
}
