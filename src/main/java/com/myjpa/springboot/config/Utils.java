package com.myjpa.springboot.config;

import java.util.List;

public class Utils {
    public static Object getFirstOneFromList(List<Object> objectList){
        if(objectList != null && objectList.size()> 0){
            return objectList.get(0);
        } else {
            return null;
        }
    }
}
