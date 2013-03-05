package net.oschina.common.cache;

/**
 * 缓存入口
 * @author Winter Lau
 */
public class OSChinaCache {
	
	private final static OSCacheConfig config = OSCacheConfig.newInstance();
	
	public static void main(String[] args) throws Exception {		
		CacheHolder cache = config.getCache("User");
		
		long ct = System.currentTimeMillis();
		for(int i=0;i<99999;i++)
			cache.put(i, "Winter Lau #" + i);
		System.out.println(System.currentTimeMillis()-ct);
		System.out.println(cache + ":" + cache.get(12));
		//cache.remove(12);
	}

}
