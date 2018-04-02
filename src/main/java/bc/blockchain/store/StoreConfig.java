package bc.blockchain.store;

import java.io.File;

public class StoreConfig {
	
	private final static String LOCAL_STORE_PATH="data";
	private final static String FILE_NAME_SUFFIX="bc_note";
	
	private static String getStroePath(String dir){
		String path=System.getProperty("BC_HOME");
		StringBuffer sb=new StringBuffer();
		sb.append(path).append(File.separatorChar).append(path).append(File.separatorChar).append(FILE_NAME_SUFFIX).append(dir);
		return sb.toString();
	}
	
	
}
