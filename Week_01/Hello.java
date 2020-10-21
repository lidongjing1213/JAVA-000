package com.lidongjing.week01;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/10/20 23:21
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
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
