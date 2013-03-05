package net.oschina.demo;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * MapDB 测试类
 * @author Winter Lau
 */
public class MapDBTest {

	public static void main(String[] args) {
		// configure and open database using builder pattern.
		// all options are available with code auto-completion.
		DB db = DBMaker.newFileDB(new File("D:\\test.cache"))
		               .encryptionEnable("password")
		               .make();

		// open existing an collection (or create new)
		ConcurrentNavigableMap<Integer,String> map = db.getTreeMap("collectionName");
		/*
		long ct = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
		map.get(1);
		map.get(2);
		}
		System.out.println("TIME:" + (System.currentTimeMillis()-ct));
		*/
		map.put(1, "one");
		map.put(2, "two");
		// map.keySet() is now [1,2]

		//db.commit();  //persist changes into disk

		map.put(3, "three");
		// map.keySet() is now [1,2,3]
		db.rollback(); //revert recent changes
		// map.keySet() is now [1,2]
		
		//db.close();
		System.exit(0);
	}

}
