import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {

	public Server() {
		try {
			socket = new DatagramSocket(4445);
			System.out.println("server ready...");
		} catch(SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void start() {
		DatagramPacket packet;
		
		while(moreClients) {
			try {
				byte[] buf = new byte[256];
				
				// receive request
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				buf = packet.getData();
				int len = packet.getLength();
				String received = (new String(buf)).substring(0, len);
				
				// prepare response
				String dString = getData(received);
				buf = dString.getBytes();
				
				// send the response to "address and "port"
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
				moreClients = false;
			}
		}
		
		socket.close();
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	private String getData(String country) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("src/temp.txt"));
		
		while(scanner.hasNext()) {
			String next = scanner.nextLine();
			if(next.startsWith(country)) {
				return next;
			}
		}
		return "no data found for: " + country;
	}

	private DatagramSocket socket = null;
	private boolean moreClients = true;

}
