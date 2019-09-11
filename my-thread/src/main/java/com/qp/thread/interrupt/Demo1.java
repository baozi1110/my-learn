package com.qp.thread.interrupt;

/**
 * 停止一个线程
 * 方法1：
 * 在循环条件中不断判断线程本身是否产生了中断，如果产生了中断就不再打印
 * @author BaoZi
 * @date 2019/9/10 15:19
 */
public class Demo1 implements Runnable {


    @Override
    public void run() {
        int num = 0;
        while (num <= Integer.MAX_VALUE/2 && !Thread.currentThread().isInterrupted()) {
            if(num % 10000 == 0){
                System.out.println(num);
            }
            num++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Demo1());
        thread.start();
        thread.sleep(1000);
        thread.interrupt();
    }

}
