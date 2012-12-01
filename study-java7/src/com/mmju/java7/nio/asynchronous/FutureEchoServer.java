package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureEchoServer {
    private static final int PORT = 5000;
    private LinkedBlockingQueue<AsyncInfo> queue;
    
    static class AsyncInfo {
        private Future<Integer> future;
        private AsynchronousSocketChannel channel;
        private ByteBuffer buffer;
 
        public AsyncInfo(Future<Integer> future,
                         AsynchronousSocketChannel channel,
                         ByteBuffer buffer) {
            this.future = future;
            this.channel = channel;
            this.buffer = buffer;
        }
    }
    
    public FutureEchoServer() throws IOException {
        AsynchronousServerSocketChannel serverChannel 
            = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(PORT));
        
        for (;;) {
            // open asynchronous socket channel
            Future<AsynchronousSocketChannel> future 
                = serverChannel.accept();

            try {
                // get Asynchronous socket channel
                AsynchronousSocketChannel channel = future.get();
                System.out.println("Connect to: " 
                                   + ((InetSocketAddress)channel
                                        .getRemoteAddress()).getHostName());
                // Watching Queue
                startWatchQueue();
                // IO Processing
                startEcho(channel);
            } catch (InterruptedException | ExecutionException ex) {}
        }
    }

    private void startEcho(AsynchronousSocketChannel channel) {
    	 
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        // Asynchronous Input
        Future<Integer> future = channel.read(buffer);
 
        // Queue Processing
        queue.offer(new AsyncInfo(future, channel, buffer));
    }
    
    private void startWatchQueue() {
        // queue for receiving data
        queue = new LinkedBlockingQueue<>();
 
        // work thread
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    for (;;) {
                        // get asynchronous info from queue
                        AsyncInfo info = (AsyncInfo) queue.take();
                        Future<Integer> future = info.future;
                    
                        // get result
                        // Timeout = 100ms 
                        int n = 0;
                        try {
                            n = future.get(100, TimeUnit.MILLISECONDS);
                        } catch(TimeoutException ex) {}
                    
                        if (future.isDone()) {
                            // The process is done
                            if (n < 0) {
                                try {
                                    info.channel.close();
                                } catch (IOException ex) {}
                                continue;
                            }
 
                            AsynchronousSocketChannel channel 
                                = info.channel;
                            ByteBuffer buffer = info.buffer;
 
                            // Output
                            buffer.flip();
                            channel.write(buffer).get();
                            
                            // Asynchronous Input
                            buffer.clear();
                            Future<Integer> nextFuture 
                                = channel.read(buffer);
                            
                            // Next input to queue
                            queue.offer(new AsyncInfo(nextFuture,
                                                      channel,
                                                      buffer));
                        } else {
                            // If process is still working
                            queue.offer(info);
                        }
                    }
                } catch (InterruptedException
                         | ExecutionException ex) {
                }
            }
        };
 
        // Work Thread Start
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(runnable);
    }
    
    public static void main(String[] args) {
        try {
            new FutureEchoServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
