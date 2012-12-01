package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class CompletionHandlerServer {

	private static final int PORT = 5000;

	public CompletionHandlerServer() throws IOException {
		AsynchronousServerSocketChannel serverChannel = 
				AsynchronousServerSocketChannel.open();
		serverChannel.bind(new InetSocketAddress(PORT));
		serverChannel.accept(serverChannel, new Acceptor());
	}

	// Acceptor Class
	class Acceptor
			implements
			CompletionHandler<AsynchronousSocketChannel, 
			AsynchronousServerSocketChannel> {

		private final ByteBuffer buffer = ByteBuffer.allocate(1024);

		public Acceptor() {
			System.out.println("an acceptor has created.");
		}

		public void completed(final AsynchronousSocketChannel channel,
				AsynchronousServerSocketChannel serverChannel) {
			System.out.println(String.format("write: name: %s", Thread
					.currentThread().getName()));
			channel.read(buffer, channel, new Reader(buffer));
			serverChannel.accept(serverChannel, new Acceptor());
		}

		public void failed(Throwable exception,
				AsynchronousServerSocketChannel serverChannel) {
			throw new RuntimeException(exception);
		}
	}

	// Reader Class
	class Reader implements
			CompletionHandler<Integer, AsynchronousSocketChannel> {

		private ByteBuffer buffer;

		public Reader(ByteBuffer buffer) {
			this.buffer = buffer;
		}

		public void completed(Integer result, AsynchronousSocketChannel channel) {
			System.out.println(String.format("read: name: %s", Thread
					.currentThread().getName()));
			if (result != null && result < 0) {
				try {
					channel.close();
					return;
				} catch (IOException ignore) {
				}
			}
			buffer.flip();
			channel.write(buffer, channel, new Writer(buffer));
		}

		public void failed(Throwable exception,
				AsynchronousSocketChannel channel) {
			throw new RuntimeException(exception);
		}
	}

	class Writer implements
			CompletionHandler<Integer, AsynchronousSocketChannel> {

		private ByteBuffer buffer;

		public Writer(ByteBuffer buffer) {
			this.buffer = buffer;
		}

		public void completed(Integer result, AsynchronousSocketChannel channel) {
			System.out.println(String.format("write: name: %s", Thread
					.currentThread().getName()));
			buffer.clear();
			channel.read(buffer, channel, new Reader(buffer));
		}

		public void failed(Throwable exception,
				AsynchronousSocketChannel channel) {
			throw new RuntimeException(exception);
		}
	}
	
    public static void main(String[] args) {
        try {
            new CompletionHandlerServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
