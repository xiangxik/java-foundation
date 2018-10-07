package kim.castle.sample.jvm;

import net.sf.cglib.beans.BeanGenerator;

//-XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+PrintGCDetails
public class CglibTest {

	public static void main(String[] args) {
		for(int i=0;i<1000000;i++) {
			BeanGenerator beanGenerator = new BeanGenerator();
			beanGenerator.addProperty("value"+i, String.class);
			Object myBean = beanGenerator.create();
//			System.out.println(myBean.getClass());
		}
	}
}
