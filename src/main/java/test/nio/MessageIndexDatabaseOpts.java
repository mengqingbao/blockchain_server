/**
 * Project Name:baomq
 * File Name:TestNio.java
 * Package Name:cn.bofowo.openmessaging.util
 * Date:2017年5月11日下午5:42:21
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package test.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.experimental.theories.Theories;

/**
 * ClassName:TestNio <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月11日 下午5:42:21 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class MessageIndexDatabaseOpts {
	
	private static int lineSize=23;
	private static int readLineNum=100;
	private static int bufSize =23 ;
	public static void main(String[] args) throws IOException {

		File fin = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");
		File fout = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");

		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

		FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);
		
		update(bufSize, fcin, rBuffer, fcout, wBuffer);

		System.out.print("OK!!!");
	}

	public static void update(int bufSize, FileChannel fcin,
			ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) throws IOException {
		try {
			byte[] bs = new byte[bufSize];
			long totalSize=fcin.size();
			float totalPage=totalSize/bufSize;
			for (int i=0;i<totalPage;i++) {
				//while(fcin.read(wBuffer)!=-1){
				//System.out.println(fcin.position());
				fcin.read(rBuffer);
				fcin.read(rBuffer);
				int rSize = rBuffer.position();
				rBuffer.rewind();
				rBuffer.get(bs);
				rBuffer.clear();
				String tempString = new String(bs, 0, rSize);
				tempString=tempString.replace("|s|", "|c|");
				System.out.println(tempString+rSize+"///");
			    wBuffer.put(tempString.getBytes());
			    wBuffer.flip();
			    fcout.write(wBuffer);
				fcout.force(true);
				wBuffer.clear();
				fcin.position(fcin.position()+1);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fcout.close();
	}
	

	public String getById(String id) throws IOException{
		File fin = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");
		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		byte[] bs = new byte[bufSize];
		long totalSize=fcin.size();
		float totalPage=totalSize/bufSize;
		
		return id;
		
	}
	
	private static int getBuffSize(){
		lineSize=23;
		int totalBuff=readLineNum*(23+1);
		return totalBuff;
	}
}
