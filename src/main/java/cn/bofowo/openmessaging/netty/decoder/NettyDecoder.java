package cn.bofowo.openmessaging.netty.decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import cn.bofowo.openmessaging.netty.ByteBufToBytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ByteBufToBytes read = new ByteBufToBytes();
        Object obj = byteToObject(read.read(in));
        out.add(obj);
	}

	public Object byteToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = null;
		try {
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				oi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}