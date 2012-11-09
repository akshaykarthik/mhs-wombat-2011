import java.net.*;
import java.io.*;

public class GameServerThreadTCP extends Thread
	{
		private Socket socket = null;
		
		public GameServerThreadTCP(Socket socket){
			super("GameServerThreadTCP");
			this.socket = socket;
		}
		
		public void run() {
			try {
				//while()   //when you are in game, breaks on quit
				
				socket.close();
			}
			catch(IOException ioe) {
				System.out.println("IOException: "+ioe);
			}
			
		}
	}
