**Week01 作业题目：**

**1.**自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后分析一下对应的字节码。

**源码**

```
public class Hello {

    /**
     * 四则运行 + - * /，if 和 for
     */
    public static void main(String[] args) {
        int num1 = 1;
        int num2 = 5;
        for (int i = 0; i < num1; i++) {
            num1 = num1 + 1;
            num2 = num2 - 1;
            if (num1 == num2) {
                num1 = num1 * num2;
            } else {
                num1 = num2 / num1;
            }
        }
    }
}
```



**操作**

编译： javac  E:\lidongjing\src\com\lidongjing\week01\Hello.java

查看字节码 javap -c  E:\lidongjing\src\com\lidongjing\week01\Hello.class



**字节码**

Compiled from "Hello.java"
public class com.lidongjing.week01.Hello {
  public com.lidongjing.week01.Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1                       //常量数字1  
       1: istore_1                       //将数字1存入栈中
       2: iconst_5                      //常量数字5
       3: istore_2                      //将数字5存入栈中
       4: iconst_0                     //常量数字0  
       5: istore_3                     //将0放入栈中
       6: iload_3                      //将0存入局部变量区中
       7: iload_1                     //将1存入局部变量区中
       8: if_icmpge     41       //比较0和1
      11: iload_1                   //将1存入局部变量区中
      12: iconst_1                //常量数字1
      13: iadd                        //1+1 即 num1 + 1
      14: istore_1           
      15: iload_2
      16: iconst_1
      17: isub
      18: istore_2
      19: iload_1
      20: iload_2
      21: if_icmpne     31
      24: iload_1
      25: iload_2
      26: imul
      27: istore_1
      28: goto          35
      31: iload_2
      32: iload_1
      33: idiv
      34: istore_1
      35: iinc          3, 1
      38: goto          6
      41: return
}





**2.**自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。

```
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
```



**3.**画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

![image-20201021185949158](C:\Users\19041663\AppData\Roaming\Typora\typora-user-images\image-20201021185949158.png)









