import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	public Client() {

	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String choice = "";
		while(!choice.equals("exit")) {
			System.out.println("pick a coutry or enter exit:");
			System.out.println("israel");
			System.out.println("egypt");
			System.out.println("germany");
			choice = in.nextLine();
			
			if(!choice.equals("exit")) {
				printAnswer(args, choice);
			}
		}
	}
	
	private static void printAnswer(String[] args, String choice) {
		String host = "localhost";
		
		if(args.length > 0) {
			host = args[0];
		}
		
		// get a datagram socket on any available port
		try {
			DatagramSocket socket = new DatagramSocket();
			
			// send request
			byte[] buf = choice.getBytes();
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
			socket.send(packet);
			
			// get response
			buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			// display response
			buf = packet.getData();
			int len = packet.getLength();
			String received = (new String(buf)).substring(0, len);
			System.out.println(received);
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
