package net.oschina.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.LoadingCache;
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

	private final static Log log = LogFactory.getLog(CacheHolderImpl.class);

	private Cache<Serializable, Serializable> cache;
	private OverflowCacheHolder overflowCache;	
	
	/**
	 * 缓存初始化
	 */
	@Override
	public void init(OSCacheConfig cfg) throws CacheException {
		
		if(StringUtils.isNotBlank(this.overflow)){
			overflowCache = cfg.getHolder(this.overflow);
			if(overflowCache == null)
				throw new CacheException("Overflow cache holder not found: " + this.overflow);
		}

		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		builder.maximumSize(size);
		if(ttl > 0)
			builder.expireAfterWrite(ttl, TimeUnit.SECONDS);

		if(overflowCache != null){
			builder.removalListener(new RemovalListener<Serializable, Serializable>() {
				public void onRemoval(RemovalNotification<Serializable, Serializable> removal) {
					if(removal.getCause() == RemovalCause.SIZE){//因为超出内存限制被移除
						//写入到二级缓存
						overflowCache.put(name, removal.getKey(), removal.getValue());
					}
					else 
					if(removal.getCause() == RemovalCause.REPLACED){//因为替换数据而调用
						//替换二级缓存上的数据
						overflowCache.put(name, removal.getKey(), removal.getValue());
					}
					else{
						//从二级缓存上删除
						overflowCache.remove(name, removal.getKey());
						System.out.println("remove!!!!!");
					}
				}
			});
			cache =  builder.build(new CacheLoader<Serializable, Serializable>() {
				public Serializable load(Serializable key) throws Exception {
					//从二级缓存中加载
					return (Serializable)overflowCache.get(name, key);
				}
			});
		}
		else
			cache = builder.build();
				
		log.info("Cache initialized: name="+name+",size="+size+",ttl="+ttl+",overflow="+overflow);
	}

	@Override
	public Object get(Serializable key) throws CacheException {
		if(cache instanceof LoadingCache){
			try{
				return ((LoadingCache<Serializable,Serializable>)cache).getUnchecked(key);
			}catch(InvalidCacheLoadException e){
				return null;
			}
		}
		return cache.getIfPresent(key);
	}

	@Override
	public Map<Serializable,Serializable> gets(List<Serializable> keys) throws CacheException {
		return cache.getAllPresent((Iterable<?>) keys.iterator());
	}

	@Override
	public void put(Serializable key, Serializable value) throws CacheException {
		cache.put(key, value);
		overflowCache.put(name, key, value);
	}

	@Override
	public void puts(Map<Serializable, Serializable> objs) throws CacheException {
		cache.putAll(objs);
		overflowCache.puts(name, objs);
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
		overflowCache.clear(name);
	}

	@Override
	public String name(){
		return this.name;
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
