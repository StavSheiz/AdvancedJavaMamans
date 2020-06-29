import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listening = true;
		
		try {
			serverSocket = new ServerSocket(7777);
		} catch(IOException e) {
			System.err.println("could not listen on port 7777");
			System.exit(1);
		}
		
		System.out.println("server ready");
		Socket socket = null;
		
		while(listening) {
			try {
				socket = serverSocket.accept();
				new EchoThread(socket).start();
			} catch(IOException e) {
				System.err.println("Accept failed");
				System.exit(1);
			}
		}
		
		serverSocket.close();
	}
	
	
}

class EchoThread extends Thread {
	private Socket socket = null;
	PrintWriter out;
	BufferedReader in;
	
	public EchoThread(Socket socket) {
		this.socket = socket;
		
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException ex) {
			System.out.println("couldn't open I/O on connection");
		}
	}
	
	public void run() {
		String inputLine;
		
		try {
			while((inputLine = in.readLine()) != null) {
				out.println(inputLine);
			}
			
			out.close();
			in.close();
			socket.close();
		} catch(IOException ex) {
			System.out.println("couldn't read from connection");
		}
	}
}
