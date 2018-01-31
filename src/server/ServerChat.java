package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

public class ServerChat {
	ServerSocket serverSocket;
	Socket socket;
	Scanner sc;

	public ServerChat() {
	}

	public ServerChat(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Ready Server");
			start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void start() throws IOException {
		socket = serverSocket.accept();
		sc = new Scanner(System.in);
		while (true) {
			System.out.println("Input Server Message");
			String msg = sc.nextLine();
			if (msg.equals("q")) {
				sc.close();
				break;
			}
			System.out.println("Server : " + msg);
		}
		System.out.println("Exit ServerChat");
	}

	// Message Sender
	class Sender implements Runnable {
		OutputStream out;
		DataOutputStream dout;
		String msg;

		public Sender() throws IOException {
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}

		public void setSendMessage(String msg) {
			this.msg = msg;
		}

		public void close() throws IOException {
			dout.close();
			out.close();
		}

		@Override
		public void run() {
			try {
				if (dout != null) {
					dout.writeUTF(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Message Receiver
	class Receiver extends Thread {
		InputStream in;
		DataInputStream din;

		public Receiver() throws IOException {
			in = socket.getInputStream();
			din = new DataInputStream(in);
		}

		public void close() throws IOException {
			in.close();
			din.close();
		}

		@Override
		public void run() {
			while (true) {
				String msg;
				try {
					msg = din.readUTF();
					System.out.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
