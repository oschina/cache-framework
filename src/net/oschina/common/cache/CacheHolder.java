package net.oschina.common.cache;

import java.util.List;
import java.util.Map;

/**
 * 定义了缓存接口
 * @author liudong
 */
public interface CacheHolder {

	/**
	 * 读取缓存对象
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public Object get(Object key) throws CacheException;
	
	/**
	 * 批量读取缓存对象
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public List<Object> gets(Object key) throws CacheException;
	
	/**
	 * 往缓存中存入对象
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Object key, Object value) throws CacheException;
	
	/**
	 * 批量存入对象
	 * @param objs
	 * @throws CacheException
	 */
	public void puts(Map<Object, Object> objs) throws CacheException;
	
	/**
	 * 更新缓存对象
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void update(Object key, Object value) throws CacheException;

	/**
	 * 获取缓存中所有的键值
	 * @return
	 * @throws CacheException
	 */
	public List<Object> keys() throws CacheException ;
	
	/**
	 * 删除缓存对象
	 * @param key
	 * @throws CacheException
	 */
	public void remove(Object key) throws CacheException;
	
	/**
	 * 清除所有缓存对象
	 */
	public void clear() throws CacheException;
	
	/**
	 * 删掉缓存
	 */
	public void destroy() throws CacheException;
	
}
