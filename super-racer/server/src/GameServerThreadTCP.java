import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameServerThreadTCP extends Thread {
	private Socket socket = null;

	public GameServerThreadTCP(Socket socket) {
		super("GameServerThreadTCP");
		this.socket = socket;
	}

	public void run() {
		try {

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String user = "Bob";
			String input, output;
			output = user + " has joined the lobby";
			out.println(output);

			while ((input = in.readLine()) != null) {
				// if the client didn't send the message
				output = in.readLine();
				System.out.println(output);
				out.println(output);
				// if they exit lobby or join a game
			}
			out.close();
			in.close();
			socket.close();
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}

	}
}
