package net.oschina.demo;

import org.agilewiki.jid.JidFactories;
import org.agilewiki.jid.collection.flenc.AppJidFactory;

/**
 * 用户对象注册
 * @author Winter Lau
 */
class UserFactory extends AppJidFactory {
	
    final public static UserFactory fac = new UserFactory();

    public UserFactory() {
        super(User.JID_TYPE, JidFactories.STRING_JID_TYPE, JidFactories.INTEGER_JID_TYPE);
    }

    protected User instantiateActor() throws Exception {
        return new User();
    }
    
}