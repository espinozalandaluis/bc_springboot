package com.bootcamp.common;

import java.util.HashMap;
import java.util.Map;

public class ResponseApi {
    public static Map<String, Object> Response(String message, int status, Object rpta){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", rpta);
        return map;
    }
}
