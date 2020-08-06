package com.simpo.tracker.common;

/**
 * 数字工具类
 */
public class IntegerTools {
    /**
     * 将给定字符串转换为数字，如果字符串为空、空串、或者不包含可转换的内容，则返回0；
     *
     * @param resource
     * @return
     */
    public static int parseInt(String resource) {
        int reply = 0;
        if (!StringTools.isBlank(resource)) {
            try {
                reply = Integer.parseInt(resource.trim());
            } catch (NumberFormatException nfe) {
                reply = -1;
                nfe.printStackTrace();
            }
        }
        return reply;
    }
}