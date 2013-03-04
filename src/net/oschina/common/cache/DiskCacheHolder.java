package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 磁盘缓存 
 * @author Winter Lau
 */
public class DiskCacheHolder extends OverflowCacheHolderBase {
	
	private final static Log log = LogFactory.getLog(DiskCacheHolder.class);

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
