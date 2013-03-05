package net.oschina.common.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;

/**
 * 磁盘缓存 
 * @author Winter Lau
 */
public class DiskCacheHolder extends OverflowCacheHolderBase {
	
	private final static Log log = LogFactory.getLog(DiskCacheHolder.class);
	
	public static void main(String[] args) {
		System.out.println(new File("C:/data/test.aaa").getPath());
	}

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
			File fPath = new File(this.path);
			if(!fPath.exists() || !fPath.isDirectory() || !fPath.canWrite())
				throw new CacheException("Path: " + this.path + " Unavailabled.");
		}
		if(!this.path.endsWith(File.separator))
			this.path += File.separator;
		log.info(getClass().getSimpleName()+" initialized, path:"+this.path);
	}
	
	/**
	 * 得到缓存文件的路径
	 * @param name
	 * @param key
	 * @return
	 */
	private String getCacheObjectPath(String name, Serializable key) {
		StringBuilder sb = new StringBuilder(this.path);
		sb.append(name.toLowerCase());
		sb.append(File.separator);
		int hcode = key.hashCode();
		sb.append(hcode % 1000);
		sb.append(File.separator);
		sb.append(hcode % 1000000);
		sb.append(File.separator);
		sb.append(hcode);
		sb.append(".cache");
		return sb.toString();
	}

	@Override
	public Object get(String name, Serializable key) throws CacheException {
		System.out.println("============ disk get ");
		File cache_file = new File(getCacheObjectPath(name, key));
		if(cache_file.exists()){
			Kryo kryo = new Kryo();
			Input input = null;
			try {
				input = new Input(new FileInputStream(cache_file));
				return kryo.readClassAndObject(input);
			} catch (IOException e) {
				log.error("IO:Unabled to read cache from " + cache_file.getPath(), e);
			} catch (KryoException e) {
				log.error("KRYO:Unabled to read cache from " + cache_file.getPath(), e);
				try {
					FileUtils.forceDelete(cache_file);
				} catch (IOException e1) {}
			} finally {
				input.close();
			}
		}
		return null;
	}

	@Override
	public Map<Serializable, Serializable> gets(String name, List<Serializable> keys) throws CacheException {
		Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>();
		for(Serializable key : keys){
			map.put(key, (Serializable)get(name, key));
		}
		return map;
	}

	@Override
	public void put(String name, Serializable key, Serializable value) throws CacheException {
		System.out.println("============ disk put ");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void puts(String name, Map<Serializable, Serializable> objs)	throws CacheException {
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
