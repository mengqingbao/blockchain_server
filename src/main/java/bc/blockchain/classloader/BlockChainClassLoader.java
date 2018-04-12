/**
 * Project Name:baomq
 * File Name:OmClassLoader.java
 * Package Name:cn.bofowo.openmessaging.classloader
 * Date:2017年5月9日下午6:17:48
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package bc.blockchain.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * ClassName:OmClassLoader <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月9日 下午6:17:48 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class BlockChainClassLoader extends ClassLoader {

	public BlockChainClassLoader(ClassLoader parentLoader){
		super(parentLoader);
	}
	
	@Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return super.loadClass(name);
        Class<?> cls = findLoadedClass(name);
        if (cls == null) {
//            cls = getSystemClassLoader().loadClass(name); (2)// SystemClassLoader 会从classpath下加载
//            if (cls == null) {(2)
            // 默认情况下， 当前cl的parent是 SystemClassLoader， 
            // 而当前cl的parent的parent 才是ExtClassLoader
                ClassLoader parent2 = getParent().getParent();
//                System.out.println("Classloader is : " + parent2); 
                
                try {
                    System.out.println("try to use ExtClassLoader to load class : " + name); 
                    cls = parent2.loadClass(name);
                } catch (ClassNotFoundException e) {
                    System.out.println("ExtClassLoader.loadClass :" + name + " Failed"); 
                }
//            }(2)
            
            if (cls == null) {
                System.out.println("try to ClassLoaderLK load class : " + name); 
                cls = findClass(name);
                
                if (cls == null) {
                    System.out.println("ClassLoaderLK.loadClass :" + name + " Failed"); 
                } else {
                    System.out.println("ClassLoaderLK.loadClass :" + name + " Successful"); 
                }
                
            } else {
                System.out.println("ExtClassLoader.loadClass :" + name + " Successful"); 
            }
        }
        return cls;
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);
        System.out.println( "try findClass " + name);
        InputStream is = null;
        Class class1 = null;
        try {
            String classPath = name.replace(".", "\\") + ".class";
//            String[] fqnArr = name.split("\\."); // split("."); 是不行的， 必须split("\\.")
//            if (fqnArr == null || fqnArr.length == 0) {
//                System.out.println("ClassLoaderLK.findClass()");
//                fqnArr = name.split("\\.");
//            } else {
//                System.out.println( name  +  fqnArr.length);
//            }
            
            String classFile =classPath;
            byte[] data = getClassFileBytes(classFile );
            
            class1 = defineClass(name, data, 0, data.length);
            if (class1 == null) {
                System.out.println("ClassLoaderLK.findClass() ERR ");
                throw new ClassFormatError();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return class1;
    }

    private byte[] getClassFileBytes(String classFile) throws Exception {
        FileInputStream fis = new FileInputStream(classFile );
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }
	
}

