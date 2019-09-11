package com.qp.thread.interrupt;

/**
 * 看起来没有问题的错误方法：
 *
 * 另外一个“常见”错误可能知名度不是太高，就是：通过volatile关键字停止线程。
 * 具体来说就是通过volatile关键字定义一个变量，通过判断变量来停止线程。
 * @author BaoZi
 * @date 2019/9/10 15:42
 * 阻塞时产生漏洞
 * @see ErrorDemo8Volatile
 */
public class ErrorDemo7Volatile implements Runnable {

    private static volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        while(num <= Integer.MAX_VALUE / 2 && !canceled){
            if(num % 100 == 0){
                System.out.println(num + "是100的倍数");
            }
            num++;
        }
        System.out.println("退出");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ErrorDemo7Volatile());
        thread.start();
        Thread.sleep(1000);
        canceled = true;
    }
}
