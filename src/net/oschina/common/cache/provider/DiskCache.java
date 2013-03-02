package net.oschina.common.cache.provider;

import java.util.Properties;

import net.oschina.common.cache.CacheHolder;
import net.oschina.common.cache.CacheProvider;
import net.oschina.common.cache.CacheException;

/**
 * 磁盘缓存 
 * @author Winter Lau
 */
public class DiskCache implements CacheProvider {

	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
