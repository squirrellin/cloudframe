package com.yaoshun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * shell命令工具集
 *
 * @author King
 * @since 2019/05/21 19:05
 */
public class ShellUtils {

    public static String exec(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[4096];
        int length;
        while ((length = bufferedReader.read(buffer, 0, 4096)) != -1) {
            stringBuilder.append(buffer, 0, length);
        }
        return stringBuilder.toString().trim();
    }

}
