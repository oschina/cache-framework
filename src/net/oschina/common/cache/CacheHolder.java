package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定义了缓存接口
 * @author liudong
 */
public interface CacheHolder {

	/**
	 * 缓存初始化
	 */
	public void init();
	
	/**
	 * 读取缓存对象
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public Object get(Serializable key) throws CacheException;
	
	/**
	 * 批量读取缓存对象
	 * @param keys
	 * @return
	 * @throws CacheException
	 */
	public Map<Serializable,Serializable> gets(List<Serializable> keys) throws CacheException;
	
	/**
	 * 往缓存中存入对象
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Serializable key, Serializable value) throws CacheException;
	
	/**
	 * 批量存入对象
	 * @param objs
	 * @throws CacheException
	 */
	public void puts(Map<Serializable, Serializable> objs) throws CacheException;
	
	/**
	 * 获取缓存中所有的键值
	 * @return
	 * @throws CacheException
	 */
	public Set<Serializable> keys() throws CacheException ;
	
	/**
	 * 删除缓存对象
	 * @param key
	 * @throws CacheException
	 */
	public void remove(Serializable key) throws CacheException;
	
	/**
	 * 清除所有缓存对象
	 */
	public void clear() throws CacheException;
	
	/**
	 * 删掉缓存
	 */
	public void destroy() throws CacheException;
	
}
