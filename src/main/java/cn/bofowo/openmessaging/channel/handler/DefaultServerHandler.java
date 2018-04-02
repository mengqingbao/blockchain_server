/**
 * Project Name:baomq
 * File Name:StringHandler.java
 * Package Name:cn.bofowo.openmessaging.server.handler
 * Date:2017年6月3日下午12:50:33
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.channel.handler;

import io.openmessaging.Message;
import io.openmessaging.demo.DefaultBytesMessage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import cn.bofowo.openmessaging.ConfigConstant;

/**
 * ClassName:StringHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月3日 下午12:50:33 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DefaultServerHandler implements Handler {

	
	public void execute(SocketChannel socketChannel){
	        try {  
	           // 等待客户端连接  
	            if(socketChannel==null){
	            	return;
	            }
	            Message message = receiveMessage(socketChannel);// 接数据   
	            
	            String response = new String("{\"code\":\"200\",\"message\":\"success\"}");  
	            responseStatus(socketChannel, response);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}
	
	 public Message receiveMessage(SocketChannel socketChannel) throws IOException {  
	        // 文件内容   
	        byte[] contents = null;  
	        // 由于我们解析时前4个字节是文件名长度  
	        int capacity = 4;  
	        ByteBuffer buf = ByteBuffer.allocate(capacity);  
	          
	        int size = 0;  
	        byte[] bytes = null;  
	     
	          
	        // 用于接收buffer中的字节数组  
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        // 文件可能会很大  
	        capacity = 1024;  
	        buf = ByteBuffer.allocate(capacity);  
	        while((size = socketChannel.read(buf)) >= 0){  
	            buf.flip();  
	            bytes = new byte[size];  
	            buf.get(bytes);  
	            baos.write(bytes);  
	            buf.clear();  
	        }  
	        contents = baos.toByteArray();  
	          System.out.println(contents);
	        Message message = new DefaultBytesMessage(contents);  
	          
	        return message;  
	    }  
	      
	    /** 
	     * 把接收的数据写到本地文件里 
	     *  
	     * @param requestObject 
	     * @throws UnsupportedEncodingException  
	     * @throws IOException  
	     */  
	    public static void writeFile(Message message) throws UnsupportedEncodingException {  
	        File file = new File(message.headers().getString(ConfigConstant.MQ_ID));  
	        FileOutputStream os = null;  
	        try {  
	            os = new FileOutputStream(file);  
	            os.write(message.getContent().getBytes());  
	            os.flush();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (os != null)  
	                    os.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	      
	    /** 
	     * 我们接受数据成功后要给客户端一个反馈 
	     * 如果是java的客户端我们可以直接给对象,如果不是最好还是给文本或字节数组 
	     * @param socketChannel 
	     * @param response 
	     */  
	    private void responseStatus(SocketChannel socketChannel, String response) {  
	        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());  
	        try {  
	            socketChannel.write(buffer);  
	            buffer.clear();  
	            // 确认要发送的东西发送完了关闭output 不然它端接收时socketChannel.read(Buffer)  
	            // 很可能造成阻塞 ，可以把这个（L）注释掉，会发现客户端一直等待接收数据  
	            socketChannel.socket().shutdownOutput();// （L）  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
}

