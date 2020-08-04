package com.black.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

/**
 * 通用常量定义
 *
 * @author King
 * @since 2018/12/20 17:32
 */
public class CommonConstants {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String CEMS_CLIENT_INVALID_VALUE = "--";

}
