package net.oschina.demo;

import org.agilewiki.jactor.factory.JAFactory;
import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.JidFactories;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.collection.vlenc.BListJid;
import org.agilewiki.jid.scalar.vlens.actor.ActorJid;

/**
 * JID List 对象演示程序
 * @author Winter Lau
 */
public class JID_ListObject_Demo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		JAFactory factory = new JAFactory(){{(new JidFactories()).initialize(this);}};       
		factory.registerActorFactory(UserFactory.fac);

		//借助  ActorJid 类实现对自定义对象的封装
		BListJid<ActorJid> actors = (BListJid<ActorJid>)factory.newActor(JidFactories.ACTOR_BLIST_JID_TYPE);
		for(int i=0;i<100;i++){
			actors.iAdd(-1);
			ActorJid  actor = actors.iGet(i);
			actor.setValue(User.JID_TYPE);
			User user = (User)actor.getValue();
			user.setName("Name#" + (i+1));
			user.setAge(i+1);
		}
		
		//序列化过程
		int slen = actors.getSerializedLength();
		AppendableBytes abytes = new AppendableBytes(slen);
		actors.save(abytes);
		
		//反序列化过程
		ReadableBytes rbytes = new ReadableBytes(abytes.getBytes(), 0);
		actors.load(rbytes);
		
		for(int i=0;i<actors.size();i++){
			ActorJid actor = actors.iGet(i);
			User user = (User)actor.getValue();
			System.out.printf("%s:%d\n", user.getName(), user.getAge());
		}
	}

}
