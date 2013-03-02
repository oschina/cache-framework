package net.oschina.common.cache.provider;

import java.util.Properties;

import net.oschina.common.cache.CacheHolder;
import net.oschina.common.cache.CacheProvider;
import net.oschina.common.cache.CacheException;

/**
 * Redis 集中式缓存
 * @author Winter Lau
 */
public class RedisCache implements CacheProvider {

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
	public void init(String name, Properties props) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CacheHolder get(String regionName) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws CacheException {
		// TODO Auto-generated method stub
		
	}

}
