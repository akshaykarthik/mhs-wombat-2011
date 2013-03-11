import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class GameServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		boolean inLobby = true;

		Properties serv = new Properties();
		serv.load(new FileReader("config.ini"));
		
		int port = Integer.parseInt(serv.getProperty("chat_port")); // Chat port. Edit
															// in config.ini
		try {
			server = new ServerSocket(port); // Make it so that the
												// server
												// DatagramSocket
												// port # can change
												// via reading a
												// text document
		} catch (IOException e) {
			System.err.println("Could not listen on port: "+port);
			System.exit(-1);
		}

		while (inLobby)
			new GameServerThreadTCP(server.accept()).start();

		new GameServerThread(Integer.parseInt(serv.getProperty("server_port")),
				Integer.parseInt(serv.getProperty("client_port"))).start();
		server.close();
	}

}
