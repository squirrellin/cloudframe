package com.black.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 全局生成唯一ID
 * @author: king
 * @create: 2019-05-28 13:57
 **/

public class RandomUtils {


    public static Long get() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return Long.valueOf(newDate + result);
    }

    public static void main(String[] args) {
        System.out.println(get());
    }
}