package kim.castle.sample.jvm;

import java.util.Vector;

//-XX:+HeapDumpOnOutOfMemoryError OOM时导出堆到文件
//-XX:+HeapDumpPath 导出OOM的路径
// -XX:OnOutOfMemoryError=a.bat 在OOM时执行一个脚本
//-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError
public class VmDump {

	public static void main(String[] args) {
		Vector<byte[]> v = new Vector<>();
		for (int i = 0; i < 25; i++) {
			v.add(new byte[1024 * 1024]);
		}
	}

}
