import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GameServerThread extends Thread {
	private DatagramSocket socket = null;
	private DatagramSocket serverSocket = null;
	private int serverPort;
	private int port;
	private String netA = "203.0.113.0";

	public GameServerThread(int port, int sPort) throws IOException {
		super("GameServerThread");
		this.port = port;
		this.serverPort = sPort;
		this.serverSocket = new DatagramSocket(this.serverPort);
		this.socket = new DatagramSocket(this.port);
	}

	public void run() {
		try {
			// Sends packet to all users in the game
			byte[] buf = new byte[1024 * 10];
			InetAddress group = InetAddress.getByName(netA); // Users join this
																// group in the
																// client
																// program
			DatagramPacket received = new DatagramPacket(buf, buf.length);
			serverSocket.receive(received); // Client sends their data of
											// character
			// position and stuff
			// Must use this data to update master
			// (server version) of game state
			String r = new String(received.getData(), 0, received.getLength());

			// ****************************
			// ********GAME UPDATES********
			// ****************************
			buf = r.getBytes();
			// Send Data to every client
			DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
					this.port);
			socket.send(packet);
		} catch (IOException ioe) { // Just in case bad stuff happens
			System.out.println("In IOException: " + ioe);
		} catch (Exception e) {
			System.out.println("In Exception: " + e);
		}

		socket.close();
	}
}
