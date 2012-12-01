package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class NonBlockingEchoServer {
	private static final int PORT = 5000;

	// Selector
	private Selector selector;

	public NonBlockingEchoServer() throws IOException {

		selector = SelectorProvider.provider().openSelector();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();

		// Setting Non-blocking
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(PORT));

		// registration of client request
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (selector.select() > 0) {

			Iterator<?> keyIterator = selector.selectedKeys().iterator();

			while (keyIterator.hasNext()) {
				// get ready key
				SelectionKey key = (SelectionKey) keyIterator.next();
				keyIterator.remove();

				if (key.isAcceptable()) {
					ServerSocketChannel channel = (ServerSocketChannel) key
							.channel();
					accept(channel);
				} else {
					SocketChannel channel = (SocketChannel) key.channel();

					// ready to read
					if (key.isReadable()) {
						read(key, channel);
					}

					// ready to write
					if (key.isValid() && key.isWritable()) {
						echo(key, channel);
					}
				}
			}
		}
	}

	// connection
	private void accept(ServerSocketChannel serverChannel) throws IOException {
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// Registration
		channel.register(selector, SelectionKey.OP_READ, buffer);
		System.out.println("Connect to: "
				+ channel.socket().getInetAddress().getHostName());
	}

	// Input
	private void read(SelectionKey key, SocketChannel channel)
			throws IOException {
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		// Input
		buffer.clear();
		if (channel.read(buffer) < 0) {
			try {
				channel.close();
			} catch (IOException ex) {
			}
		} else {
			// Registration
			channel.register(selector, SelectionKey.OP_WRITE, buffer);
		}
	}

	// Output
	private void echo(SelectionKey key, SocketChannel channel)
			throws IOException {
		// get buffer
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		// output
		buffer.flip();
		channel.write(buffer);

		// Registration
		channel.register(selector, SelectionKey.OP_READ, buffer);
	}

	public static void main(String[] args) {
		try {
			new NonBlockingEchoServer();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
