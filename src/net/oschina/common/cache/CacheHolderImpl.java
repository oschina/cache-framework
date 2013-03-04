package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * 缓存实现
 * @author Winter Lau
 */
public class CacheHolderImpl implements CacheHolder {

	private Cache<Serializable, Serializable> cache;

	/**
	 * 缓存初始化
	 */
	public void init(){
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		builder.maximumSize(size);
		if(ttl > 0)
			builder.expireAfterWrite(ttl, TimeUnit.SECONDS);

		builder.removalListener(new RemovalListener<Serializable, Serializable>() {
			public void onRemoval(RemovalNotification<Serializable, Serializable> removal) {
				if(removal.getCause() == RemovalCause.SIZE){//因为超出内存限制被移除
					//写入到磁盘
				}
				else 
				if(removal.getCause() == RemovalCause.REPLACED){//因为替换数据而调用
					//替换磁盘上的数据
				}
				else{
					//从磁盘上删除
				}
			}
		});
		cache = builder.build(new CacheLoader<Serializable, Serializable>() {
			public Serializable load(Serializable key) throws Exception {
				//TODO: 从二级缓存中加载
				return null;
			}
		});
	}

	@Override
	public Object get(Serializable key) throws CacheException {
		return cache.getIfPresent(key);
	}

	@Override
	public Map<Serializable,Serializable> gets(List<Serializable> keys) throws CacheException {
		return cache.getAllPresent((Iterable<?>) keys.iterator());
	}

	@Override
	public void put(Serializable key, Serializable value) throws CacheException {
		cache.put(key, value);
	}

	@Override
	public void puts(Map<Serializable, Serializable> objs) throws CacheException {
		cache.putAll(objs);
	}

	@Override
	public Set<Serializable> keys() throws CacheException {
		return cache.asMap().keySet();
	}

	@Override
	public void remove(Serializable key) throws CacheException {
		cache.invalidate(key);
	}

	@Override
	public void clear() throws CacheException {
		cache.invalidateAll();
	}

	@Override
	public void destroy() throws CacheException {
		cache.invalidateAll();
	}

	private String name;
	private int size;
	private int ttl;
	private String overflow;
	
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

	public String getOverflow() {
		return overflow;
	}

	public void setOverflow(String overflow) {
		this.overflow = overflow;
	}

}
