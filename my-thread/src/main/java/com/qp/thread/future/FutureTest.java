package com.qp.thread.future;

import java.util.concurrent.*;

/**
 * Future代表一个异步计算的结果。
 * 它提供了方法来检查是否计算已经完成，还是正在计算而处于等待状态，并且也提供了获取计算结果 方法。
 * 当计算完成后，只能通过get方法来获取执行结果，必要的话该方法会阻塞。通过cancel方法可以取消计算。一旦计算已经完成，便无法取消。
 * 主要方法：
 * cancel()：取消任务
 * get()：等待任务执行完成，并获取执行结果
 * get(long timeout, TimeUnit unit)：在指定的时间内会等待任务执行，超时则抛异常。
 *
 * @author BaoZi
 * @date 2019/9/26 15:59
 */
public class FutureTest {
    public static void functionTimeoutTest2() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
                    Thread.sleep(10000);
                    return "success";
                }
        );

        try {
            // String result = future.get(5, TimeUnit.SECONDS);
            String result = future.get(15, TimeUnit.SECONDS);
            System.out.println("result:" + result);
        } catch (TimeoutException e) {
            System.out.println("超时了!");
        }
    }

    /**
     * FutureTask的一般用法，它最大的好处就是我们可以将任务交给执行器后执行其他操作，然后再从里面得到任务的结果。
     * 这里必须要注意，只有FutureTask这种既实现Runnable又实现Callable才能够通过executor()递交给ExecutorService,
     * 而Future不行，只能通过submit()，因为executor()要求的参数是一个实现了Runnable的类。
     *
     * @throws Exception
     */
    public static void functionTimeoutTest3() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<>(() -> {
            Thread.sleep(10000);
            return "success";
        }
        );
        executorService.execute(future);

        try {
            // String result = future.get(5, TimeUnit.SECONDS);
            String result=future.get(15,TimeUnit.SECONDS);
            System.out.println("result:" + result);
        } catch (TimeoutException e) {
            System.out.println("超时了!");
        }
    }

    public static void main(String[] args) throws Exception {
        // functionTimeoutTest2();
        functionTimeoutTest3();
    }

}
