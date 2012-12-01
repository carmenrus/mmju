package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleFutureEchoServer {
    private static final int PORT = 5000;
    
    public SimpleFutureEchoServer() throws IOException {
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
                // IO Processing
                startEcho(channel);
            } catch (InterruptedException | ExecutionException ex) {}
        }
    }

    private void startEcho(AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            for (;;) {
                // Input
                buffer.clear();
                Future<Integer> future = channel.read(buffer);
            
                // get Input Result
                if (future.get() < 0) {
                    try {
                        channel.close();
                    } catch (IOException ex) {}
                }
                
                // output
                buffer.flip();
                channel.write(buffer).get();
            }
        } catch (InterruptedException
                 |ExecutionException ex) {}
    }

    public static void main(String[] args) {
        try {
            new SimpleFutureEchoServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
