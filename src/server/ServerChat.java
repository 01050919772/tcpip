package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerChat {
	ServerSocket serverSocket;
	Socket socket;
	Scanner sc;
	String msg;
	Sender sender;

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

		Receiver receiver = new Receiver();
		receiver.start();
		
		while (true) {
			System.out.println("Input Server Message");
			msg = sc.nextLine();
			Sender sender = new Sender(); 
			Thread t = new Thread(sender);
			if (msg.equals("q")) {
				sc.close();
				sender.close();
				receiver.close();
				break;
			}
			sender.setSendMessage(msg);
			t.start();
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
					System.out.println("Exit Server User");
					break;
				}
			}
		}
	}
}
