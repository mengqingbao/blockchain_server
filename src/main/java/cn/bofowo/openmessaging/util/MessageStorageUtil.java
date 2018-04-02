/**
 * Project Name:baomq
 * File Name:MessageStorageUtil.java
 * Package Name:cn.bofowo.openmessaging.util
 * Date:2017年5月4日下午6:12:19
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package cn.bofowo.openmessaging.util;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import cn.bofowo.openmessaging.entity.MessageStorageFile;

/**
 * ClassName:MessageStorageUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月4日 下午6:12:19 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class MessageStorageUtil {

	private static MessageStorageUtil ms = null;
	private static KeyValue properties;

	public MessageStorageUtil(KeyValue properties) {

	}

	public static MessageStorageUtil getInstance(KeyValue properties) {
		if (ms == null) {
			ms = new MessageStorageUtil(properties);
		}
		return ms;
	}

	public void createMessageStorageFile(Message message) {
//		MessageStorageFile ms = new MessageStorageFile(properties, message);
//		ms.saveFile(message);
	}

	/**
	 * 
	 * queryMessage：不全部加载大文件检索硬盘数据。
	 *
	 * @author mqb
	 * @return
	 * @throws IOException
	 * @since JDK 1.7
	 */
	public Message queryMessage() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(new File(""));
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
		return null;
	}

}
