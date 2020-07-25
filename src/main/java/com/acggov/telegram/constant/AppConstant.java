package com.acggov.telegram.constant;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created: Nekoer
 * Desc: 公共常量
 * Date: 2020/5/13 15:27
 */
public class AppConstant {


    /**
     * 时间校验
     */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF2 = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SDF3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat SDF4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 判断是否是数字
     */
    public static final String ISNUMBER = "^-?[0-9]+$";

    /**
     * HTTP状态码
     */
    public static final List<Integer> HTTP_CODE_SUCCESS = Arrays.asList(200, 201);
    public static final List<Integer> HTTP_CODE_ERROR = Arrays.asList(100, 101, 202, 203, 204, 205, 206, 300, 301, 302, 303, 304, 305, 306, 307, 308, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 500, 501, 502, 503, 504, 505);

}
