package net.oschina.common.cache;

/**
 * 缓存入口
 * @author Winter Lau
 */
public class OSChinaCache {
	
	private final static OSCacheConfig config = OSCacheConfig.newInstance();
	
	public static void main(String[] args) throws Exception {
		System.out.println(config);
	}

}
