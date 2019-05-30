package com.selune.wechatordering.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: Selune
 * @Date: 5/30/19 5:16 PM
 */

public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
