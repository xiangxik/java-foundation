package kim.castle.sample.jvm;

//-Xmx20m -Xms20m -XX:NewRatio=1 -XX:SurvivorRatio=3 -XX:+PrintGCDetails
public class VmTest1 {

	public static void main(String[] args) {
		byte[] b = null;
		for (int i = 0; i < 10; i++) {
			b = new byte[1 * 1024 * 1024];
		}
	}
}
