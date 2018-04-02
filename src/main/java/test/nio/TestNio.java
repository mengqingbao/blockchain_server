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
import java.nio.channels.FileChannel;

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
public class TestNio {

	public static void main(String[] args) throws FileNotFoundException {

		int bufSize = 1000;
		File fin = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");
		File fout = new File("/Users/hanahyu/mengqingbao/alicode/test/5M1.txt");

		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

		FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);

		readFileByLine(bufSize, fcin, rBuffer, fcout, wBuffer);

		System.out.print("OK!!!");
	}

	/* 读文件同时写文件 */
	public static void readFileByLine(int bufSize, FileChannel fcin,
			ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) {
		String enterStr = "\n";
		try {
			byte[] bs = new byte[bufSize];

			int size = 0;
			StringBuffer strBuf = new StringBuffer("");
			// while((size = fcin.read(buffer)) != -1){
			while (fcin.read(rBuffer) != -1) {
				int rSize = rBuffer.position();
				rBuffer.rewind();
				rBuffer.get(bs);
				rBuffer.clear();
				String tempString = new String(bs, 0, rSize);
			    System.out.println(tempString);
				// System.out.print("<200>");

				int fromIndex = 0;
				int endIndex = 0;
				int i=0;
				while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
					String line = tempString.substring(fromIndex, endIndex);
					line = new String(strBuf.toString() + line+enterStr);
					//System.out.print(line+(i++));
					// System.out.print("</over/>");
					// write to anthone file
					writeFileByLine(fcout, wBuffer, line);
					System.out.println(strBuf.toString()+"/////");
					strBuf.delete(0, strBuf.length());
					
					fromIndex = endIndex + 1;
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 写文件 */
	public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer,
			String line) {
		try {
			// write on file head
			// fcout.write(wBuffer.wrap(line.getBytes()));
			// wirte append file on foot
			fcout.write(wBuffer.wrap(line.getBytes()), fcout.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
