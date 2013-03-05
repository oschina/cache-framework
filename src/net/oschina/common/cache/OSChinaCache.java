package net.oschina.common.cache;

/**
 * 缓存入口
 * @author Winter Lau
 */
public class OSChinaCache {
	
	private final static OSCacheConfig config = OSCacheConfig.newInstance();
	
	public static void main(String[] args) throws Exception {
		CacheHolder cache = config.getCache("User");
		
		cache.put(12, "Winter Lau");
		System.out.println(cache + ":" + cache.get(12));
		cache.remove(12);
	}

}
