package com.cs.spring.mvc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {

	/**
	 * 序列化一个对象
	 */
	public static byte[] serialize(Object object) {
		if (object == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
		} catch (IOException ex) {
			throw new IllegalArgumentException("对象序列化失败，对象类型: " + object.getClass(), ex);
		}
		return baos.toByteArray();
	}

	/**
	 * 反序列化对象
	 */
	public static Object deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			return ois.readObject();
		} catch (IOException ex) {
			throw new IllegalArgumentException("对象反序列化失败", ex);
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("对象反序列化失败，对象类型", ex);
		}
	}

}
