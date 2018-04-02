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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

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
public class IndexStorageFile {

	public IndexStorageFile(BytesMessage message) {
		throw new UnsupportedClassVersionError("不支持该构造方法");
	}

	public IndexStorageFile(KeyValue properties, Message message) {
		this.timestamp = System.currentTimeMillis();
		this.fileName = message.properties().getString(ConfigConstant.MQ_ID);
		this.filePath = ConfigConstant.MQ_MESSAGE_PATH;

	}

	
	
	public IndexStorageFile(String path, String fileName) {
		this.fileName = fileName;
		this.filePath = path;
		this.file = new File(this.filePath + "/" + fileName);

		FileInputStream in;
		ObjectInputStream objIn = null;
		try {
			in = new FileInputStream(file);
			objIn = new ObjectInputStream(in);
		} catch (IOException e) {
			e.printStackTrace();
	} finally {
			try {
				objIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	//保存文件
	public void saveFile() {
		this.file = new File(this.filePath + "/" + this.fileName);
		 FileWriter fw = null;
	        BufferedWriter writer = null;
	        try {
	            fw = new FileWriter(file);
	            writer = new BufferedWriter(fw);
	            while(link.isEmpty()){
	                writer.write(link.pop().toString());
	                writer.newLine();//换行
	            }
	            writer.flush();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                writer.close();
	                fw.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
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
	/**
	 * 行读取文件，获得linkedList （消息文件名）
	 * @author mqb
	 * @param file
	 * @return
	 * @since JDK 1.7
	 */
	public LinkedList<String> getDataFromHDD(File file) {
		link=new LinkedList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				link.add(tempString);
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
		return link;
	}

	

	private String filePath;
	private String fileName;
	private long timestamp;
	private String lockerName;
	private String status;
	private long id;
	private File file;
	private LinkedList link;

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
