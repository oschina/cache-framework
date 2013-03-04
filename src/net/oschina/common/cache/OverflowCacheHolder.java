package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 二级缓存接口(磁盘或者网络)
 * @author Winter Lau
 */
public interface OverflowCacheHolder {

	public String name();

	/**
	 * 缓存初始化
	 */
	public void init() throws CacheException;
	
	/**
	 * 读取缓存对象
	 * @param name
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public Object get(String name, Serializable key) throws CacheException;
	
	/**
	 * 批量读取缓存对象
	 * @param name
	 * @param keys
	 * @return
	 * @throws CacheException
	 */
	public Map<Serializable,Serializable> gets(String name, List<Serializable> keys) throws CacheException;
	
	/**
	 * 往缓存中存入对象
	 * failfast semantics
	 * @param name
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(String name, Serializable key, Serializable value) throws CacheException;
	
	/**
	 * 批量存入对象
	 * @param name
	 * @param objs
	 * @throws CacheException
	 */
	public void puts(String name, Map<Serializable, Serializable> objs) throws CacheException;
	
	/**
	 * 删除缓存对象
	 * @param name
	 * @param key
	 * @throws CacheException
	 */
	public void remove(String name, Serializable key) throws CacheException;
	
	/**
	 * 清除所有缓存对象
	 * @param name
	 */
	public void clear(String name) throws CacheException;
	
}
