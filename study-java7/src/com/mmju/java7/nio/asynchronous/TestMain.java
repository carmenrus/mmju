package com.mmju.java7.nio.asynchronous;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TestMain {

	public static void main(String[] args) {

		Socket socket = null;

		try {
			String server = "localhost";
			int servPort = 5000;
			byte[] data = "Hello, Net world".getBytes();
			byte[] msg = new byte[data.length];

			socket = new Socket(server, servPort);
			System.out.println("Connection To Server");

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			out.write(data);
			System.out.println("Send：" + new String(data));

			// get Server Message
			int totalBytesRcvd = 0;
			int bytesRcvd;
			while (totalBytesRcvd < data.length) {
				if ((bytesRcvd = in.read(msg, totalBytesRcvd, data.length
				        - totalBytesRcvd)) == -1) {
					throw new SocketException("Fail");
				}
				totalBytesRcvd += bytesRcvd;
			} // while end
			System.out.println("Recieve：" + new String(msg));

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}