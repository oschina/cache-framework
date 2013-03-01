package net.oschina.common.cache.provider;

import java.util.Properties;

import net.oschina.common.cache.Cache;
import net.oschina.common.cache.CacheException;
import net.oschina.common.cache.CacheProvider;

/**
 * 内存缓存
 * @author Winter Lau
 */
public class RamCache implements CacheProvider {

	@Override
	public void init(String name, Properties props) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cache get(String regionName) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws CacheException {
		// TODO Auto-generated method stub
		
	}

}
