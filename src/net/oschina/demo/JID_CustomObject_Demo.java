package net.oschina.demo;

import org.agilewiki.jactor.factory.JAFactory;
import org.agilewiki.jid.JidFactories;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;

/**
 * JID 自定义对象演示程序
 * @author Winter Lau
 */
public class JID_CustomObject_Demo {

	public static void main(String[] args) throws Exception {
		
		JAFactory factory = new JAFactory(){{(new JidFactories()).initialize(this);}};
		factory.registerActorFactory(UserFactory.fac);
		
		RootJid rootJid = (RootJid) factory.newActor(JidFactories.ROOT_JID_TYPE);
		long ct = System.currentTimeMillis();
		rootJid.setValue(User.JID_TYPE);
		
		User user = (User)rootJid.getValue();
		user.setName("Winter Lau");
		user.setAge(98);
		
		int slen = rootJid.getSerializedLength();
		
		byte[] sdatas = new byte[slen];
		rootJid.save(sdatas, 0);
		
		rootJid.load(sdatas, 0, slen);
		User user1 = (User)rootJid.getValue();
		
		System.out.printf("%dms->%s:%d\n", (System.currentTimeMillis()-ct), user1.getName(), user1.getAge());
		
	}

}
