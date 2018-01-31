package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	public static void main(String[] args) {
		ServerChat server = null;
		server = new ServerChat(8888);
	}
}
