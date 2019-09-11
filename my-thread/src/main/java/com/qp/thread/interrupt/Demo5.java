package com.qp.thread.interrupt;

/**
 * 假如要被停止的线程正在执行某个子方法，这个时候该如何处理中断？
 * 方法二：
 *  在子方法中捕获中断异常，但是捕获以后当前线程的中断控制位将被清除，父方法执行时将无法感知中断。
 *  所以此时在子方法中重新设置中断，这样父方法就可以通过对中断控制位的判断来处理中断：
 * @author BaoZi
 * @date 2019/9/10 15:34
 */
public class Demo5 implements Runnable{

    @Override
    public void run() {
        while(true && !Thread.currentThread().isInterrupted()){//每次循环判断中断控制位
            System.out.println("go");
            throwInterrupt();
        }
        System.out.println("检测到了中断，循环打印退出");
    }

    private void throwInterrupt(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();//重新设置中断
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Demo5());
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}
