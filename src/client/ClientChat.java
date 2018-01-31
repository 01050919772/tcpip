package client;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClientChat {
	String ip;
	int port;
	Socket socket;
	Scanner sc;
	
	public ClientChat() {}
	public ClientChat(String ip, int port) {
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			System.out.println("Connected Server");
			start();
		} catch (IOException e) {
			System.out.println("Connection Refused");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void start() {
		sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input Client Message");
			String msg = sc.nextLine();
			if(msg.equals("q")) {
				sc.close();
				break;
			}
			System.out.println("Client : " + msg);
		}
		System.out.println("Exit Chatting");
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
					if(dout != null) {
						dout.writeUTF(msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		// Message Receiver
		class Receiver extends Thread{
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
				while(true) {
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
