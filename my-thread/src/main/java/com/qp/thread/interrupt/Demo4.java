package com.qp.thread.interrupt;

/**
 * 假如要被停止的线程正在执行某个子方法，这个时候该如何处理中断？
 * 方法1：
 *  在子方法中把中断异常上抛给父方法，然后在父方法中处理中断：
 * @author BaoZi
 * @date 2019/9/10 15:32
 */
public class Demo4 implements Runnable{

    @Override
    public void run() {
        try{//在父方法中捕获中断异常
            while(true){
                System.out.println("go");
                throwInterrupt();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("检测到中断，保存错误日志");
        }
    }

    private void throwInterrupt() throws InterruptedException {//把中断上传给父方法
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Demo4());
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}
