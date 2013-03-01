package net.oschina.common.cache;

import java.util.Properties;

/**
 * 缓存管理的实现
 * @author liudong
 */
public interface CacheProvider {

	/**
	 * 初始化缓存提供者
	 * @param name
	 * @param props
	 * @throws CacheException
	 */
	public void init(String name, Properties props) throws CacheException ;
	
	/**
	 * Configure the cache
	 * @param regionName the name of the cache region
	 * @throws CacheException
	 */
	public Cache get(String regionName) throws CacheException;
	
	/**
	 * 关闭缓存
	 * @throws CacheException
	 */
	public void close() throws CacheException ;
	
}
