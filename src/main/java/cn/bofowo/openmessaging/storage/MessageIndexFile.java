/**
 * Project Name:baomq
 * File Name:MessageIndexFile.java
 * Package Name:cn.bofowo.openmessaging.storage
 * Date:2017年5月14日下午11:02:43
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ClassName:MessageIndexFile <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月14日 下午11:02:43 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MessageIndexFile {

	
	private int lineSize=23; //没一行的字节数量
	private int readLineNum=100; //设置每次映射到内存的数据条数。
	
	private static File file;
	private static FileChannel fcin;
	private static FileChannel fcout;
	public MessageIndexFile(){
		 
		 
	}
	
	public static void createFileChannel(){
		file = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");
		 try {
			fcout = new RandomAccessFile(file, "rws").getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() throws IOException{
		fcout.close();
	}
	
	public static void main(String[] args) throws IOException {
		MessageIndexFile mif=new MessageIndexFile();
		File fin = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");
		File fout = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");

		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(mif.getBuffSize());

		FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(mif.getBuffSize());
		
		mif.update(fcin, rBuffer, fcout, wBuffer);

		System.out.print("OK!!!");
		
		for(int i=0;i<19;i++){
			//mif.insert(i+"test");
		}
	}

	
	public void update(FileChannel fcin,
			ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) throws IOException {
		try {
			byte[] bs = new byte[getBuffSize()];
			long totalSize=fcin.size();
			float totalPage=totalSize/getBuffSize()+1;
			for (int i=0;i<totalPage;i++) {
				//while(fcin.read(wBuffer)!=-1){
				//System.out.println(fcin.position());
				fcin.read(rBuffer);
				int rSize = rBuffer.position();
				rBuffer.rewind();
				rBuffer.get(bs);
				rBuffer.clear();
				String tempString = new String(bs, 0, rSize);
				int replaceSize=tempString.length();
				fcin.position(fcin.position()-replaceSize);
				tempString=tempString.replace("|c|", "|s|");
				tempString="";
				System.out.println(tempString+rSize+"///");
			  //  wBuffer.put(tempString.getBytes());
			    wBuffer.flip();
			    fcout.write(wBuffer.wrap(tempString.getBytes()));
				fcout.force(true);
				wBuffer.clear();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fcout.close();
	}
	
	
	
	public synchronized void insert(String str) throws IOException{
		str=str+"\n";
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(getBuffSize());
		try {
			byte[] bs = new byte[getBuffSize()];
			long totalSize=fcin.size();
		    wBuffer.flip();
		    fcout.write(wBuffer.wrap(str.getBytes()),totalSize);
			fcout.force(true);
			wBuffer.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private int getBuffSize(){
		int totalBuff=readLineNum*(lineSize+1);
		return totalBuff;
	}

}

