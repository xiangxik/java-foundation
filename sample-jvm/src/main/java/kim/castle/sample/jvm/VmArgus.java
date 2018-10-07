package kim.castle.sample.jvm;

//-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
// -Xmx最大堆
// -Xms最小堆
// -Xmn新生代
// -XX:NewRatio新生代比老年代
// -XX:SurvivorRatio两个幸存代和eden的比， 8 表是 survivor:eden=2:8
public class VmArgus {

	public static void alloc() {
		byte[] b = new byte[2000];
		b[0] = 1;
	}

	public static void printXm() {
		System.out.print("Xmx=");
		System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0 + "M");

		System.out.print("free mem=");
		System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0 + "M");

		System.out.print("total mem=");
		System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0 + "M");
	}

	public static void main(String[] args) {
		// byte[] c = new byte[20000];
		long b = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println(e - b);

		byte[] c = new byte[1024 * 1024];
		printXm();
	}
}
