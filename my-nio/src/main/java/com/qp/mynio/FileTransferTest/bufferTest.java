package com.qp.mynio.FileTransferTest;

import java.nio.ByteBuffer;

public class bufferTest {
    /**
     * 容量Capacity：缓冲区能够容纳的数据元素的最大数量。容量在缓冲区创建时被设定，并且永远不能被改变。(底层是数组嘛)
     * 上界Limit：缓冲区里的数据的总数，代表了当前缓冲区中一共有多少数据。
     * 位置Position：下一个要被读或写的元素的位置。Position会自动由相应的 get( )和 put( )函数更新。
     * 标记Mark：一个备忘位置。用于记录上一次读写的位置。
     *
     */

    public static void main(String[] args) {
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->"+byteBuffer.limit());
        System.out.println("初始时-->position--->"+byteBuffer.position());
        System.out.println("初始时-->capacity--->"+byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s = "Javaqp";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->"+byteBuffer.limit());
        System.out.println("put完之后-->position--->"+byteBuffer.position());
        System.out.println("put完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 取出数据
        // 一般我们称filp()为“切换成读模式
        byteBuffer.flip();
        System.out.println("flip完之后-->limit--->"+byteBuffer.limit());
        System.out.println("flip完之后-->position--->"+byteBuffer.position());
        System.out.println("flip完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("flip完之后-->mark--->" + byteBuffer.mark());
        System.out.println("--------------------------------------");

        // 创建一个limit()大小的字节数组(因为就只有limit这么多个数据可读)
        byte[] bytes = new byte[byteBuffer.limit()];
        // 将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);
        // 输出数据
        System.out.println(new String(bytes, 0, bytes.length));

        System.out.println("get完之后-->limit--->"+byteBuffer.limit());
        System.out.println("get完之后-->position--->"+byteBuffer.position());
        System.out.println("get完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("get完之后-->mark--->" + byteBuffer.mark());
        System.out.println("--------------------------------------");

        byteBuffer.clear();
        System.out.println("clear完之后-->limit--->"+byteBuffer.limit());
        System.out.println("clear完之后-->position--->"+byteBuffer.position());
        System.out.println("clear完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("clear完之后-->mark--->" + byteBuffer.mark());
        // 再次取出发现数据没有被清空，因为它的作用仅仅是将position、limit等这些标志位复原，并非清空了真实的数据
        // 放入其他数据发现之前的数据被覆盖了一部分，但是剩下的取不出来了
        byteBuffer.put("php".getBytes());
        byteBuffer.flip();
        byte[] bytes1 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes1);
        System.out.println(new String(bytes1, 0, bytes1.length));
        System.out.println("--------------------------------------");

    }
}
