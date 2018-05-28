package com.wx.ad.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapSort {  
    public static void main(String[] args) {  
        Map<String, String> map = new TreeMap<String, String>();  
        map.put("KFC", "kfc");  
        map.put("WNBA", "wnba");  
        map.put("NBA", "nba");  
        map.put("CBA", "cba");  
        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序  
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {  
            System.out.println(entry.getKey() + " " + entry.getValue());  
        }  
        System.out.println("--------------------------");
        
        Map<String, String> map1 = new TreeMap<String, String>();  
        map1.put("KFC", "kfc");  
        map1.put("WNBA", "wnba");  
        map1.put("NBA", "nba");  
        map1.put("CBA", "cba");  
        Map<String, String> resultMap1 = sortMapByValue(map); //按Value进行排序  
        for (Map.Entry<String, String> entry : resultMap1.entrySet()) {  
            System.out.println(entry.getKey() + " " + entry.getValue());  
        }  
    }  
      
    /** 
     * 使用 Map按key进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, String> sortMapByKey(Map<String, String> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());  
        sortMap.putAll(map);  
        return sortMap;  
    }  
    
    /** 
     * 使用 Map按value进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, String> sortMapByValue(Map<String, String> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(map.entrySet());  
        Collections.sort(entryList, new MapValueComparator());  
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
        Map.Entry<String, String> tmpEntry = null;  
        while (iter.hasNext()) {  
            tmpEntry = iter.next();  
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
        }  
        return sortedMap;  
    }  
    
}  
  
