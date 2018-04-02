package bc.blockchain.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class BeanJsonUtil {
	
    /**   
     * 从一个JSON 对象字符格式中得到一个java对象   
     *    
     * @param jsonString   
     * @param beanCalss   
     * @return   
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {  
           
        JSONObject jsonObject = JSONObject.parseObject(jsonString);  
        T bean = (T) JSONObject.toJavaObject(jsonObject, beanCalss);  
           
        return bean;  
           
    }  
   
    /**   
     * 将java对象转换成json字符串   
     * 
     * @param bean   
     * @return   
     */  
    public static String beanToJson(Object bean) {  
   
        String json = JSONObject.toJSONString(bean);  
           
        return json;  
   
    }  
       
   
   
   
   
   
    /** 
     * 将java对象List集合转换成json字符串   
     * @param beans 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static String beanListToJson(List beans) {  
           
        StringBuffer rest = new StringBuffer();  
           
        rest.append("[");  
           
        int size = beans.size();  
           
        for (int i = 0; i < size; i++) {  
               
            rest.append(beanToJson(beans.get(i))+((i<size-1)?",":""));  
   
        }  
           
        rest.append("]");  
           
        return rest.toString();  
   
    }  
       
   
    /**   
     * 从json HASH表达式中获取一个map，改map支持嵌套功能   
     * 
     * @param jsonString   
     * @return   
     */  
    @SuppressWarnings({ "unchecked" })  
    public static Map jsonToMap(String jsonString) {  
           
        JSONObject jsonObject = JSONObject.parseObject(jsonString);  
        Iterator keyIter = jsonObject.keySet().iterator();  
        String key;  
        Object value;  
        Map valueMap = new HashMap();  
   
        while (keyIter.hasNext()) {  
               
            key = (String) keyIter.next();  
            value = jsonObject.get(key).toString();  
            valueMap.put(key, value);  
               
        }  
   
        return valueMap;  
    }  
}
