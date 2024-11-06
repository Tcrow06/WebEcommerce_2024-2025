package com.webecommerce.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtils {
    public static <T> T toModel (Class<T> tclass, HttpServletRequest request)  {
        T obj = null;
        try {
            obj = (T) tclass.newInstance();
            BeanUtils.populate(obj, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e ) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
