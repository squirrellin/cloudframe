package com.black.util;

import java.util.UUID;


/**
 * @description: uuid工具类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }

    public static void main(String[] args){
        for (int i = 0; i<100; i++) {
            System.out.println(UUIDUtils.getUUIDInOrderId());
        }
    }
}
