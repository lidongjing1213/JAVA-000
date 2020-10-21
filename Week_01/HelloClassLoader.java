package com.lidongjing.week01;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义classLoader〉
 *
 * @author lidongjing
 * @Date: 2020/10/21 14:55
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HelloClassLoader extends ClassLoader {

    private static final String PATH = "E:\\lidongjing\\src\\com\\lidongjing\\week01\\Hello.xlass";

    public static void main(String[] args) {
        try {
            Class helloClass = new HelloClassLoader().findClass("Hello");
            try {
                Method helloMethod = helloClass.getMethod("hello");
                try {
                    helloMethod.invoke(helloClass.newInstance());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] helloBytes = new byte[0];
        try {
            helloBytes = readFileToBytes(PATH);
            for (int i = 0; i < helloBytes.length; i++) {
                helloBytes[i] = (byte) (255 - helloBytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, helloBytes, 0, helloBytes.length);
    }


    private byte[] readFileToBytes(String name) throws IOException {
        File file = new File(name);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[(int) fileSize];
        int n = 0;
        while ((n = fileInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, n);
        }
        bufferedOutputStream.close();
        fileInputStream.close();
        return buffer;
    }
}
