package com.simpo.tracker.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 配置文件工具
 * 用于读取配置文件中的参数
 * 使用方法：String value = SystemTools.getInstance().getProperty("test1.properties", "a");
 * 参数1：配置文件名称 参数2：key
 * 配置文件需放在本目录下
 */
public class SystemTools {
    private Properties props = null;

    public static SystemTools getInstance() {
        return new SystemTools();
    }

    private void loadProperties(String path) {
        props = new Properties();
        InputStream is = null;
        try {
            is = this.getClass().getResourceAsStream(path);
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is == null) {
                return;
            }

            try {
                is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public String getProperty(String path, String propName) {
        if (props == null) {
            loadProperties(path);
        }
        return props.getProperty(propName);
    }

    public static String getProperty(Properties properties, String key, String encoding) throws UnsupportedEncodingException {
        //param check
        if (properties == null) {
            return null;
        }

        //如果此时value是中文，则应该是乱码
        String value = properties.getProperty(key);
        if (value == null) {
            return null;
        }

        //编码转换，从ISO8859-1转向指定编码
        return new String(value.getBytes("ISO8859-1"), encoding);
    }

	public static void main(String args[])
	{
		System.out.println(SystemTools.getInstance().getProperty("system.properties", "ischeck"));
	}
}
