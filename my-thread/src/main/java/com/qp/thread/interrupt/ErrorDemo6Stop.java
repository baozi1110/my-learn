package com.qp.thread.interrupt;

/**
 * 常见错误停止线程例子
 *      在外部直接把运行中的线程停止掉。这种暴力的方法很有可能造成脏数据。
 * @author BaoZi
 * @date 2019/9/10 15:38
 */
public class ErrorDemo6Stop implements Runnable{
    /**
     * 模拟指挥军队，以一个连队为单位领取武器，一共有5个连队，一个连队10个人
     * 我们模拟军队发放武器，规定一个连为一个单位，每个连有10个人。
     * 当我们直接从外部通过stop方法停止武器发放后。
     * 很有可能某个连队正处于发放武器的过程中，导致部分士兵没有领到武器。
     */
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println("第" + (i + 1) + "个连队开始领取武器");
            for(int j = 0; j < 10; j++){
                System.out.println("第" + (j + 1) + "个士兵领取武器");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第" + (i + 1) + "个连队领取武器完毕");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ErrorDemo6Stop());
        thread.start();
        Thread.sleep(2500);
        //使用stop时可以停止线程，但是导致武器发放到某个连队中时断掉
        thread.stop();
        //直接使用interrupt无法停止线程
        // thread.interrupt();
    }
}
