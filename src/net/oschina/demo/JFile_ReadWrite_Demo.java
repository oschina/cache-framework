package net.oschina.demo;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.MailboxFactory;
import org.agilewiki.jactor.factory.JAFactory;
import org.agilewiki.jfile.ForcedWriteRootJid;
import org.agilewiki.jfile.JFile;
import org.agilewiki.jfile.ReadRootJid;
import org.agilewiki.jfile.block.Block;
import org.agilewiki.jfile.block.LBlock;
import org.agilewiki.jid.JidFactories;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;

/**
 * 使用 JFile 进行文件读写操作
 * @author Winter Lau
 */
public class JFile_ReadWrite_Demo {

	public static void main(String[] args) throws Exception {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        Mailbox mailbox = mailboxFactory.createMailbox();
        JAFactory factory = new JAFactory();
        //factory.initialize(mailbox);
        (new JidFactories()).initialize(mailbox, factory);
		factory.registerActorFactory(UserFactory.fac);
        JAFuture future = new JAFuture();

        JFile jFile = new JFile();
        jFile.initialize(mailbox, factory);
        Path path = FileSystems.getDefault().getPath("LFileTest.jalog");
        System.out.println(path.toAbsolutePath());
        jFile.open(
                path,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

		RootJid rootJid = (RootJid) factory.newActor(JidFactories.ROOT_JID_TYPE);
		long ct = System.currentTimeMillis();
		rootJid.setValue(User.JID_TYPE);
		
		User user = (User)rootJid.getValue();
		user.setName("Winter Lau");
		user.setAge(98);
		
        Block block = new LBlock();
        block.setRootJid(rootJid);
        (new ForcedWriteRootJid(block)).send(future, jFile);
        System.out.println("block.getCurrentPosition()="+block.getCurrentPosition());

        Block block2 = new LBlock();
        (new ReadRootJid(block2)).send(future, jFile);
        RootJid rj2 = block2.getRootJid(mailbox, factory);
        System.out.println(rj2);
        
        User user2 = (User)rj2.getValue();

        jFile.close();
        mailboxFactory.close();
        
        
		System.out.printf("%dms->%s:%d\n", (System.currentTimeMillis()-ct), user2.getName(), user2.getAge());
		System.exit(0);
	}
	
}
