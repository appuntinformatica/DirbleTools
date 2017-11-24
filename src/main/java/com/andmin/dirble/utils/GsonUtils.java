package com.andmin.dirble.utils;

import com.google.gson.*;
import com.google.gson.reflect.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class GsonUtils {
    private static final Logger log = LogManager.getLogger();

    public static <T> List<T> toList(String json, Class<T> clazz) {
        if (null == json) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<T>(){}.getType());
    }
    
    public static <T> List<T> stringToArray(String json, Class<T[]> clazz) {
        if (null == json) {
            return null;
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }
    
    public static <T> String arrayToString(List<T> data, boolean pettyPrinting) {
        Gson gson = null;
        if ( pettyPrinting ) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        } else {
            gson = new GsonBuilder().create();
        }
        return gson.toJson(data);
    }

    public static <T1, T2> String mapToString(Map<T1, T2> data, boolean pettyPrinting) {
        Gson gson = null;
        if ( pettyPrinting ) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        } else {
            gson = new GsonBuilder().create();
        }
        return gson.toJson(data);
    }
}
