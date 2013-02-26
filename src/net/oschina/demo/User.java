package net.oschina.demo;

import org.agilewiki.jid.collection.flenc.AppJid;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

/**
 * 序列化对象
 * @author Winter Lau
 */
class User extends AppJid {
	
	public final static String JID_TYPE = "User";
	
    private StringJid getNameJid() throws Exception {
        return (StringJid) _iGet(0);
    }
    
    private IntegerJid getAgeJid() throws Exception {
        return (IntegerJid) _iGet(1);
    }
    
    public String getName() throws Exception {
        return getNameJid().getValue();
    }
    
    public void setName(String name) throws Exception {
        getNameJid().setValue(name);
    }
    
    public int getAge() throws Exception {
        return getAgeJid().getValue();
    }
    
    public void setAge(int age) throws Exception {
        getAgeJid().setValue(age);
    }
}