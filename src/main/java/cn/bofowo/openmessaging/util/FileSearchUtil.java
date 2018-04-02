/**
 * Project Name:baomq
 * File Name:FileSearchUtil.java
 * Package Name:cn.bofowo.openmessaging.util
 * Date:2017年5月10日下午11:49:27
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package cn.bofowo.openmessaging.util;

import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ClassName:FileSearchUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月10日 下午11:49:27 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class FileSearchUtil {
	public static List<String> sortFileName(String filePath) {
		List<File> files = Arrays.asList(new File(filePath).listFiles());
		Collections.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.isDirectory() && o2.isFile())
					return -1;
				if (o1.isFile() && o2.isDirectory())
					return 1;
				return o1.getName().compareTo(o2.getName());
			}
		});
		List<String> result = new ArrayList<String>();
		for (File file : files) {
			result.add(file.getName());
		}
		return result;

	}

	public static boolean changeFile(String fName, int start, int len)
			throws Exception {
		// 创建一个随机读写文件对象
		java.io.RandomAccessFile raf = new java.io.RandomAccessFile(fName, "rw");
		long totalLen = raf.length();
		System.out.println("文件总长字节是: " + totalLen);
		// 打开一个文件通道
		java.nio.channels.FileChannel channel = raf.getChannel();
		// 映射文件中的某一部分数据以读写模式到内存中
		java.nio.MappedByteBuffer buffer = channel.map(
				FileChannel.MapMode.READ_WRITE, start, len);
		// 示例修改字节
		for (int i = 0; i < len; i++) {
			byte src = buffer.get(i);
			buffer.put(i, (byte) (src - 31));// 修改Buffer中映射的字节的值
			System.out.println("被改为大写的原始字节是:" + src);
		}
		buffer.force();// 强制输出,在buffer中的改动生效到文件
		buffer.clear();
		channel.close();
		raf.close();
		return true;
	}
}
