package net.oschina.common.cache;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

/**
 * 磁盘缓存 
 * @author Winter Lau
 */
public class DiskCacheHolder extends OverflowCacheHolderBase {
	
	private final static Log log = LogFactory.getLog(DiskCacheHolder.class);
	private final static String CODE = "oschina";
	private final static String FILENAME = "oschina";
	private DB disk;
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void init() {
		super.init();
		String spath = System.getProperty(this.path);
		if(StringUtils.isNotBlank(spath)){
			File fPath = new File(spath);
			if(!fPath.exists() || !fPath.isDirectory() || !fPath.canWrite())
				throw new CacheException("Path: " + this.path + " Unavailabled.");
			this.path = spath;
		}
		else{
			String the_path = FilenameUtils.normalize(this.path);
			File fPath = new File(the_path);
			if(!fPath.exists() || !fPath.isDirectory() || !fPath.canWrite())
				throw new CacheException("Path: " + this.path + " Unavailabled.");
			this.path = the_path;
			
		}
		if(!this.path.endsWith(File.separator))
			this.path += File.separator;
		//初始化 MapDB
		disk = DBMaker.newFileDB(new File(this.path + FILENAME)).encryptionEnable(CODE).journalDisable().closeOnJvmShutdown().make();
		
		log.info(getClass().getSimpleName()+" initialized, path:"+this.path);
	}
	
	@Override
	public Object get(String name, Serializable key) throws CacheException {
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		Object r = map.get(key);
		System.out.printf("<=== %s: %s -> %s\n", name, key, r);
		return r;
	}

	@Override
	public Map<Serializable, Serializable> gets(String name, List<Serializable> keys) throws CacheException {
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		Map<Serializable, Serializable> results = new HashMap<Serializable, Serializable>();
		for(Serializable key : keys){
			Serializable v = (Serializable)map.get(key);
			if(v != null)
				results.put(key, v);
		}
		return results;
	}

	@Override
	public void put(String name, Serializable key, Serializable value) throws CacheException {
		System.out.printf("===> %s: %s -> %s\n", name, key, value);
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		map.put(key, value);
		//disk.commit();
	}

	@Override
	public void puts(String name, Map<Serializable, Serializable> objs)	throws CacheException {
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		map.putAll(objs);
		//disk.commit();
	}

	@Override
	public void remove(String name, Serializable key) throws CacheException {
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		map.remove(key);
		//disk.commit();
	}

	@Override
	public void clear(String name) throws CacheException {
		HTreeMap<Object,Object> map = disk.getHashMap(name);
		map.clear();
		//disk.commit();
	}

}
