package net.oschina.common.cache;

import java.io.Serializable;
import java.util.*;

/**
 * 缓存入口
 * @author Winter Lau
 */
public class OSChinaCache {
	
	private final static OSCacheConfig config = OSCacheConfig.newInstance();
	
	public static void main(String[] args) throws Exception {		
		final CacheHolder cache = config.getCache("User");
		long ct = System.currentTimeMillis();
		/*
		for(int i=0;i<100;i++){
			new Thread(){{}
			@Override
			public void run() {
				List<MyObject> objs = new ArrayList<MyObject>();
				for(int i=0;i<100;i++){
					objs.add(new MyObject(this.getName()+"#"+i, i));
				}
				cache.put(this.getName(), (Serializable)objs);
			}}.start();
		}
		*/
		System.out.println(System.currentTimeMillis()-ct);
		List<MyObject> objs = (List<MyObject>)cache.get("Thread-32");
		for(MyObject obj : objs){
			System.out.printf("name:%s,age:%d\n",obj.getName(),obj.getAge());
		}
		//System.out.println(cache + ":" + cache.get(12));
		//cache.remove(12);
	}

	public static class MyObject implements Serializable {
		private String name;
		private int age;
		private int age2;
		public MyObject(){}
		public MyObject(String name, int age){
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
}
