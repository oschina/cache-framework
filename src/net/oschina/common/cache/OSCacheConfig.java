package net.oschina.common.cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;
import org.apache.commons.io.IOUtils;

/**
 * 缓存配置信息
 * @author Winter Lau
 */
public class OSCacheConfig {

	private HashMap<String, OverflowCacheHolder> holders;
	private HashMap<String, CacheHolder> caches;
	
	private OSCacheConfig(){
		holders = new HashMap<String, OverflowCacheHolder>();
		caches = new HashMap<String, CacheHolder>();
	}
	
	public static OSCacheConfig newInstance() {
		OSCacheConfig cfg = newInstance(OSCacheConfig.class.getResourceAsStream("/cache.xml"));
		cfg.init();
		return cfg;
	}
	
	private void init() {
		for(OverflowCacheHolder holder : holders.values()){
			holder.init();
		}
		for(CacheHolder cache : caches.values()){
			cache.init(this);
		}
	}
	
	public static OSCacheConfig newInstance(String configPath) throws FileNotFoundException {
		return newInstance(new FileInputStream(configPath));
	}
	
	public OverflowCacheHolder getHolder(String name){
		return holders.get(name);
	}
	
	public void addHolder(OverflowCacheHolder pvd) {
		holders.put(pvd.name(), pvd);
	}
	
	public void addCache(CacheHolder cache) {
		caches.put(cache.name(), cache);
	}
	
	private static OSCacheConfig newInstance(InputStream config) {
		OSCacheConfig cache = new OSCacheConfig();
		Digester dig = new Digester();
		dig.setValidating(false);
		dig.setRules(new ExtendedBaseRules());
		dig.push(cache);
		
		String key = "oscache/holder";
		dig.addObjectCreate(key, "class", OverflowCacheHolder.class);
		dig.addSetProperties(key);
		dig.addBeanPropertySetter(key + "/?");
		dig.addSetNext(key, "addHolder");

		key = "oscache/cache";
		dig.addObjectCreate(key, "class", CacheHolderImpl.class);
		dig.addSetProperties(key);
		dig.addBeanPropertySetter(key + "/?");
		dig.addSetNext(key, "addCache");
		
		try{
			return (OSCacheConfig)dig.parse(config);
		}catch(Exception e){
			throw new CacheException("Unable to init cache", e);
		}finally{
			IOUtils.closeQuietly(config);
		}
	}

}
