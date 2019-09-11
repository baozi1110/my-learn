package com.qp.thread.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * volatile方法有一个潜在的大漏洞，就是若线程进入了阻塞状态，我们将不能通过修改volatile变量来停止线程
 * @author BaoZi
 * @date 2019/9/10 15:45
 */
public class ErrorDemo8Volatile {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);//1s足够让生产者把阻塞队列塞满

        Consumer consumer = new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(storage.take() + "被消费");
            Thread.sleep(100);//让消费者消费慢一点，给生产者生产的时间
        }
        System.out.println("消费者消费完毕");
        producer.canceled = true;//让生产者停止生产（实际情况是不行的，因为此时生产者处于阻塞状态，volatile不能唤醒阻塞状态的线程）
    }
}

class Producer implements Runnable{

    public volatile boolean canceled = false;

    private BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try{
            while(num < Integer.MAX_VALUE / 2 && !canceled){
                if(num % 100 == 0){
                    this.storage.put(num);
                    System.out.println(num + "是100的倍数，已经被放入仓库");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者停止生产");
        }
    }
}

class Consumer{
    private BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        return Math.random() < 0.95 ? true : false;
    }
}