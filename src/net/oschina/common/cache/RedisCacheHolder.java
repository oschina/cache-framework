package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Redis 集中式缓存
 * @author Winter Lau
 */
public class RedisCacheHolder extends OverflowCacheHolderBase {

	private final static Log log = LogFactory.getLog(DiskCacheHolder.class);

	private String host;
	private int port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		log.info(getClass().getSimpleName()+" initialized");
	}

	@Override
	public Object get(String name, Serializable key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Serializable, Serializable> gets(String name, List<Serializable> keys)
			throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(String name, Serializable key, Serializable value) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void puts(String name, Map<Serializable, Serializable> objs)
			throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String name, Serializable key) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(String name) throws CacheException {
		// TODO Auto-generated method stub
		
	}

}
