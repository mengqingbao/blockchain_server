/**
 * Project Name:baomq
 * File Name:MessageStorageFIle.java
 * Package Name:cn.bofowo.openmessaging.entity
 * Date:2017年5月4日下午6:19:51
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package cn.bofowo.openmessaging.entity;

import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import cn.bofowo.openmessaging.ConfigConstant;

/**
 * ClassName:MessageStorageFIle <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月4日 下午6:19:51 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class MessageStorageFile {

	public MessageStorageFile(BytesMessage message) {
		throw new UnsupportedClassVersionError("不支持该构造方法");
	}

	private void checkEnvironment() {
		File dir = new File(this.filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

	}

	public void updateIndexFile(String operateType) {
		switch (operateType) {
		case ConfigConstant.INDEX.ADD:
			this.appendByFileWriter(message.headers().getString(
					ConfigConstant.MQ_ID));
			break;
		case ConfigConstant.INDEX.REMOVE:

			break;
		default:
			break;
		}
	}

	public MessageStorageFile(String path, String fileName) {
		this.fileName = fileName;
		this.filePath = path + "/data/";
		this.checkEnvironment();
		this.file = new File(this.filePath + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFile(Message msgObj) {
		FileOutputStream out;
		ObjectOutputStream objOut = null;
		try {
			out = new FileOutputStream(file);
			objOut = new ObjectOutputStream(out);
			objOut.writeObject(msgObj);
			objOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Message readFile() {
		if (!file.exists()) {
			return null;
		}
		FileInputStream out;
		ObjectInputStream inputStream = null;
		try {
			out = new FileInputStream(file);
			inputStream = new ObjectInputStream(out);
			return (Message) inputStream.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void delete() {
		if (file.exists()) {
			file.delete();
		}
	}

	public void lock() {
		if (file.exists()) {
			File newFile = new File(file.getName() + ".lock");
			file.renameTo(newFile);
		}
	}

	public void unlock() {
		if (file.exists()) {
			String name = file.getName().replace(".lock", "");
			File newFile = new File(name);
			file.renameTo(newFile);
		}
	}

	public void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 随即读取文件实现方法，备用
	 */
	public void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 实现的方法修改 ，该方法飞废弃暂时不用
	 * 
	 * @author mqb
	 * @param fileName
	 * @param content
	 * @since JDK 1.7
	 */
	public void remove(String fileName, String content) {
		try {
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void appendByFileWriter(String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(indexFileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String filePath;
	private String fileName;
	private long timestamp;
	private String lockerName;
	private String status;
	private long id;
	private File file;
	private Message message;
	private String indexFileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getLockerName() {
		return lockerName;
	}

	public void setLockerName(String lockerName) {
		this.lockerName = lockerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
