package cn.bofowo.openmessaging.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil
{

	public static boolean isEmpty(String str)
	{
		if(str==null || "".equals(str))
		{
			return true;
		}
		else
		{
			if(!"".equals(str.trim()))
			{
			  return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	public static boolean isEmail(String email)
	{
		if(email==null){
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
		Pattern regex = Pattern.compile(check);  
		Matcher matcher = regex.matcher(email);  
		return matcher.matches(); 
	}
	
	public static String subString(String str,int length){
		if(!StringUtil.isEmpty(str)){
			if(str.length()>length){
				return str.substring(0, length-1)+"...";
			}else{
				return str;
			}
		}else{
			return "";
		}
	}
	
	public static String getImageId(String str){
		if(!StringUtil.isEmpty(str)&&str.contains(";")){
			return str.substring(0,str.indexOf(";"));
		}
		if(!StringUtil.isEmpty(str)){
			return str;
		}
		return "000";
	}
	

	
	
	
	
	/**
	 * 
	* @Title: isEmptyCollection
	* @Description: TODO(判断集合是否为空集合)
	* @param @param obj (List or Map)
	* @return boolean    (如果该集合为null或者该集合的size==0返回ture，否则返回fasle)
	* @author ChenXun
	* @date 2015年4月26日
	* @throws
	 */
	public static boolean  isEmptyCollection(Object obj){
		if(obj instanceof Map){
			return obj==null||((Map)obj).size()==0? true:false;
		}
		else if(obj instanceof List){
			return obj==null||((List)obj).size()==0? true:false;
		}
		else
			
		return true;
		
	}
	
	/**
	 * 把一个字符串的一个空格或多个空格替换成一个空格，并切割成多个字符串，返回一个字符串数组
	 * @param str 
	 * @return String[] 数组  
	 */ 
	public static String[] splitStr(String str){
		 if(str==null||StringUtil.isEmpty(str))
			return null;
		
		
		String values = str.replaceAll(" {2,}", " "); 
		values = str.replaceAll(" ", "-"); 
		String[] array = str.split("-");
		return array;	
	}
	
	/**
	 * 获取QQaccess_token
	 * @param str
	 * @return
	 */
	public static String getAccess_token(String str){
		return  str.substring(str.indexOf("=")+1,str.indexOf("&"));
	}
	
	
	/**
	 * 获取微信的sccess_token
	 * @return
	 */
	public static String getWeixinAccess_token(String str){
		// {"access_token":"OezXcEiiBSKSxW0eoylIeIGVIJJtlQ82_aP8OqwEn2mCNXQGQ03Q0XsN20MrP-2u2RhNyIVq_EJwWKXLM-5E2TQYblw0v5y6a-HVYlB7vDRMYcIDf1X1tgi3TmfPlx0bYA4R4O1EkQBVjg8ClUwYxQ","expires_in":7200,"refresh_token":"OezXcEiiBSKSxW0eoylIeIGVIJJtlQ82_aP8OqwEn2mCNXQGQ03Q0XsN20MrP-2u5os3ls6H55ABLkiFrzs-YBuw1ym0aVWfl9qddkc5AZTaCPsZK1O68dMcYHJKuka0DJvUaikCggNAjah9ZrjAGQ","openid":"ot3mQs9QMWV5o5XSTZ-YgiYFeoXo","scope":"snsapi_login","unionid":"o-SeIuProDf6_kAGoRt1mCITK7oA"}
		String result = str.substring(str.indexOf("scope")-31,str.indexOf("scope")-3);
		return result;
		
	}
	
	/**
	 * 返回一个当前日期加上一个八位随机数的State
	 * @return
	 */
	public static String getWeixinState(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		dateStr = dateStr+getRandom();
		
		return dateStr+getRandom();
	}
	
	public static String getRandom(){
		String str = "";
		//产生随机数
		Random random = new Random();


		for(int i=0;i<8;i++){

			str+=random.nextInt(10);

		}
		return str;
	}
	
	
	/**
	 * 传入一个11位数的手机号码，返回一个130******01的字符串
	 * @param str
	 * @return
	 */
	public static String getMaskPhone(String str){
		if(isEmpty(str)||str.trim().length()!=11)
			return "";
	    String rsu = str.substring(str.length()-8, str.length()-2);
		String result = str.replace(rsu, "******");
		
		return result;
		
	}
	
	
	/**
	 * 获取微博返回的uid
	 * @param str
	 * @return
	 */
	public static String getWeiBoUid(String str){
		String result = null;
		//{"access_token":"2.002TxrJG014gfi330934cb3b0dFSPE","remind_in":"126898","expires_in":126898,"uid":"5642644015"}
		result = str.substring(str.lastIndexOf(":")+2, str.length()-2);
		return result;
	}
	
	
	public static String getNowDate(){
		Date d2 =  new Date();
        //获得年份 （注意年份要加上1900，这样才是日期对象d2所代表的年份）
        int year = d2.getYear() + 1900;
        //获得月份  （注意月份要加1，这样才是日期对象d2所代表的月份）
        int month = d2.getMonth() + 1;
        //获得日期
        int date = d2.getDate();
        //获得小时
        int hour = d2.getHours();
        //获得分钟
        int minute = d2.getMinutes();
        //获得秒
        int second = d2.getSeconds();
		return year+"年"+month+"月"+date+"日"+hour+"点"+minute+"分"+second+"秒";
	}
	
	public static boolean checkSameDay(Date tempDate){
		boolean flag = false;
		//传入的日期
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdfTemp.format(tempDate);
		
		//当前日期
		SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdfNow.format(new Date());
		
		if(date1.equals(date2)){
			flag = true;
		}
		return flag;
	}
	
	public static boolean checkArrayContainStr(String[] arry,String temp){
		boolean flag = false;
		String tager = "";
		if(arry!=null||arry.length>0){
			for(int i=0;i<arry.length;i++){
				tager = arry[i];
				if(tager.indexOf(temp)>0){
					flag = true;
				}
			}
		}
		
		return flag;
		
	}
	
	public static boolean checkSetContainStr(Set<String> setList,String temp){
		boolean flag = false;
		Iterator<String> it = setList.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();  
		  if(temp.equals(str)){
			  flag = true;
		  }
		} 
		
		return flag;
		
	}
	
	public static String getAddress(String address){
	   if(StringUtil.isEmpty(address))
		   return "";
	   String temp=address.replaceAll("_地级市", "");
	   temp=temp.replaceAll("_市、县、区", "");
	   return temp;
		
		
	}
	
	
	
	public static String parseString(String str,int length){
		if(StringUtil.isEmpty(str)){
			return "";
		}
		
		return str.length()>(length+1)?str.substring(0,length)+"...":str;
		 
		
	};
	
	//判断输入的字符是否是整数
    public static boolean isInteger(String str) {    
		    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		    return pattern.matcher(str).matches();    
		  }  
    
    
    
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z" };

    //生成短8位的UUID
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
		    String str = uuid.substring(i * 4, i * 4 + 4);
		    int x = Integer.parseInt(str, 16);
		    shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	
	}
	
	
	
	public static boolean isMobileNo(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");  
		  
		Matcher m = p.matcher(mobiles);  
		  
		  
		return m.matches();  
		
	}

	public static boolean isContainStr(String content,String sub){
		if(StringUtil.isEmpty(content)){
			return false;
		}
		if(content.contains(sub)){
			return true;
		}
		return false;
	}
    
    public static void main(String args[]){
//    	for(int i=0;i<10;i++){
//    		System.out.println(generateShortUuid());
//    	}
//    	boolean flag = isMobileNo("1251251");
//    	if(!flag){
//    		System.out.println(flag);
//    	}
    	
    	
    }

	public static boolean isNotEmpty(String str) {
		
		if(str==null || "".equals(str))
		{
			return false;
		}
		else
		{
			if(!"".equals(str.trim()))
			{
			  return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public static String getString(ByteBuffer buffer)  
    {  
        Charset charset = null;  
        CharsetDecoder decoder = null;  
        CharBuffer charBuffer = null;  
        try  
        {  
            charset = Charset.forName("UTF-8");  
            decoder = charset.newDecoder();  
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空  
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());  
            return charBuffer.toString();  
        }  
        catch (Exception ex)  
        {  
            ex.printStackTrace();  
            return "";  
        }  
    }  
}

