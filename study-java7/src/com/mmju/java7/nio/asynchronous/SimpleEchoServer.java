package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleEchoServer {
    private static final int PORT = 5000;
    private ExecutorService service;
    
    public SimpleEchoServer() throws IOException {
        // new thread pool
        service = Executors.newCachedThreadPool();

        // server socket
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(PORT));

        for (;;) {
            // wait
            SocketChannel channel = serverChannel.accept();
            System.out.println("Connect to: " 
                               + channel.socket()
                                   .getInetAddress().getHostName());

            // access from client
            startEcho(channel);
        }
    }

    public void startEcho(final SocketChannel channel) {
        // I/O processing
        Runnable runnable = new Runnable() {
            public void run() {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                    
                try {
                    for (;;) {
                        // Input
                        buffer.clear();
                        if (channel.read(buffer) < 0) {
                            break;
                        }
                        
                        // Output
                        buffer.flip();
                        channel.write(buffer);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        channel.close();
                    } catch(IOException ex) {}
                }
            }
        };

        // execute by thread pool
        service.execute(runnable);
    }

    public static void main(String[] args) {
        try {
            new SimpleEchoServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
