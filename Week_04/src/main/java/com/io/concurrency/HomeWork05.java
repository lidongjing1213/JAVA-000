package com.io.concurrency;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/11/11 15:05
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HomeWork05 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 异步执行 下面方法
        FutureTask<Integer> result = new FutureTask(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        new Thread(result).start();
        try {
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
