package net.oschina.common.cache;

/**
 * 二级缓存基类
 * @author Winter Lau
 */
public abstract class OverflowCacheHolderBase implements OverflowCacheHolder {

	private String name;
	
	@Override
	public String name(){
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see net.oschina.common.cache.OverflowCacheHolder#init()
	 */
	@Override
	public void init() throws CacheException {		
	}

}
