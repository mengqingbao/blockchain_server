/**
 * Project Name:baomq
 * File Name:BaoPropertiesUtil.java
 * Package Name:cn.bofowo.openmessaging.properties
 * Date:2017年5月20日下午12:07:54
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package cn.bofowo.openmessaging.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.util.StringUtil;

/**
 * ClassName:BaoPropertiesUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月20日 下午12:07:54 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class BaoPropertiesUtil {
	// 属性文件的路径
	static String path = ConfigConstant.STORE_PATH;
	static String profile = path + "/bao.properties";
	/**
	 * 采用静态方法
	 */
	private static Properties props = new Properties();
	static {
		try {
			chekcDir();
			props.load(new FileInputStream(profile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.exit(-1);
		}
	}

	/**
	 * 读取属性文件中相应键的值
	 * 
	 * @param key
	 *            主键
	 * @return String
	 */
	public static String getKeyValue(String key) {
		return props.getProperty(key);
	}

	public static void chekcDir() {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		File file2 = new File(profile);
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 根据主键key读取主键的值value
	 * 
	 * @param filePath
	 *            属性文件路径
	 * @param key
	 *            键名
	 */
	public static synchronized int readValue(String key) {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream(profile));
		} catch (FileNotFoundException e1) {
		} catch (IOException e1) {
		}
		String value = props.getProperty(key);
		if (StringUtil.isEmpty(value)) {
			value = "1";
		}
		int max = Integer.valueOf(value);
		max = max + 1;
		BaoPropertiesUtil.writeProperties(key, String.valueOf(max));
		try {
			return max;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public static synchronized int readMinValue(String key) {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream(profile));
		} catch (FileNotFoundException e1) {
		} catch (IOException e1) {
		}
		String value = props.getProperty(key);
		if (StringUtil.isEmpty(value)) {
			value = "1";
		}
		int min = Integer.valueOf(value);
		min = min + 1;
		int max = Integer.valueOf(props.getProperty(key.replace("_min", "_max")));
		if(min>max){
			return 0;
		}
		BaoPropertiesUtil.writeProperties(key, String.valueOf(min));
		try {
			return min;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param keyname
	 *            键名
	 * @param keyvalue
	 *            键值
	 */
	public static synchronized void writeProperties(String keyname,
			String keyvalue) {
		OutputStream fos = null;
		try {
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			fos = new FileOutputStream(profile);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "Update '" + keyname + "' value");
			fos.flush();
			fos.close();
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}
	}

	/**
	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param keyname
	 *            键名
	 * @param keyvalue
	 *            键值
	 */
	public static void updateProperties(String keyname, String keyvalue) {
		try {
			props.load(new FileInputStream(profile));
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(profile);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}
	}

	// 测试代码
	public static void main(String[] args) {
		readValue("MAIL_SERVER_PASSWORD");
		writeProperties("MAIL_SERVER_INCOMING", "327@qq.com");
	}
}
