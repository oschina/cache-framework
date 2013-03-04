package net.oschina.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo 的演示程序
 * @author Winter Lau
 */
public class KryoDemo {

	public static void main(String[] args) throws Exception {
		Kryo kryo = new Kryo();
		// ...
		Output output = new Output(new FileOutputStream("file.bin"));
		List<MyObject> lst = new ArrayList<MyObject>();
		lst.add(new MyObject("Winter Lau",18));
		lst.add(new MyObject("Oschina",5));
		kryo.writeClassAndObject(output, lst);
		output.close();
		// ...
		Input input = new Input(new FileInputStream("file.bin"));
		@SuppressWarnings("unchecked")
		List<MyObject> someObject = (List<MyObject>)kryo.readClassAndObject(input);
		for(MyObject so : someObject){
			System.out.printf("Name:%s,Age:%d\n",so.getName(), so.getAge());
		}
		input.close();
	}

	public static class MyObject {
		private String name;
		private int age;
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
