package net.oschina.common.cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;
import org.xml.sax.SAXException;

/**
 * 缓存配置信息
 * @author Winter Lau
 */
public class OSCacheConfig {

	private List<CacheProvider> providers;
	private List<CacheHolder> caches;
	
	private OSCacheConfig(){
		providers = new ArrayList<CacheProvider>();
		caches = new ArrayList<CacheHolder>();
	}
	
	public static OSCacheConfig newInstance() throws IOException, SAXException {
		return newInstance(OSCacheConfig.class.getResourceAsStream("/cache.xml"));
	}
	
	public static OSCacheConfig newInstance(String configPath) throws IOException, SAXException {
		return newInstance(new FileInputStream(configPath));
	}
	
	public void addProvider(CacheProvider pvd) {
		providers.add(pvd);
	}
	
	public void addCache(CacheHolder cache) {
		cache.init();
		caches.add(cache);
	}
	
	private static OSCacheConfig newInstance(InputStream config) throws IOException, SAXException {
		OSCacheConfig cache = new OSCacheConfig();
		Digester dig = new Digester();
		dig.setValidating(false);
		dig.setRules(new ExtendedBaseRules());
		dig.push(cache);
		
		String key = "oscache/provider";
		dig.addObjectCreate(key, "class", CacheProvider.class);
		dig.addSetProperties(key);
		dig.addBeanPropertySetter(key + "/?");
		dig.addSetNext(key, "addProvider");

		key = "oscache/cache";
		dig.addObjectCreate(key, "class", CacheHolderImpl.class);
		dig.addSetProperties(key);
		dig.addBeanPropertySetter(key + "/?");
		dig.addSetNext(key, "addCache");
		
		try{
			return (OSCacheConfig)dig.parse(config);
		}finally{
			config.close();
		}
	}

}
