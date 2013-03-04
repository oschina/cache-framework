package net.oschina.common.cache;

import java.util.List;
import java.util.Map;

/**
 * 缓存实现
 * @author Winter Lau
 */
public class CacheHolderImpl implements CacheHolder {

	private String name;
	private int size;
	private int ttl;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	@Override
	public Object get(Object key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> gets(Object key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void puts(Map<Object, Object> objs) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> keys() throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Object key) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() throws CacheException {
		// TODO Auto-generated method stub

	}

}
