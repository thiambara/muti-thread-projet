package com.multi.backend.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Conv<T> {

    public T pour(T toObject, T fromOject, List<String> filedsMapping) {
        Class<? extends Object> c = toObject.getClass();
        Field[] fields = c.getDeclaredFields();

        for (Field f : fields) {
            String fieldName = f.getName();
            if (filedsMapping==null || filedsMapping.contains(fieldName)) {
                try {
                    fieldName = this.capitalize(fieldName);

                    String getMethodeName = "get" + fieldName;
                    String setMethodeName = "set" + fieldName;
                    Class<? extends Object> [] cArg = new Class<?>[1];
                    cArg[0] = f.getType();
                    Method getMethod = c.getDeclaredMethod(getMethodeName);
                    Method setMethod = c.getDeclaredMethod(setMethodeName, cArg);
                    if(getMethod.invoke(fromOject)!=null){
                        setMethod.invoke(toObject, getMethod.invoke(fromOject));
                    }

                } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return toObject;
    }

    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
