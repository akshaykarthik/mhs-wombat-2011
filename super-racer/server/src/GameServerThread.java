import java.net.*;
import java.io.*;

public class GameServerThread extends Thread
	{
		private DatagramSocket	socket	= null;
		private String netA = "203.0.113.0";
		public GameServerThread() throws IOException
			{
				this(27000);
			}

		public GameServerThread(int socket) throws IOException
			{
				super("GameServerThread");
				this.socket = new DatagramSocket(socket);
			}

		public void run()
			{
				try
					{
						// Game State and Stuff

						// Sends packet to all users in the game
						byte[] buf = new byte[256];
						InetAddress group = InetAddress
								.getByName(netA);           // Users would join
															// this group in the
															// client program
						DatagramPacket packet = new DatagramPacket(buf,
								buf.length, group, 27000); // Sends to everyone
														   // in the
														   // InetAddress group
						socket.send(packet);
					}
				catch (IOException ioe)
					{ // Just in case bad stuff happens
						System.out.println("In IOException: " + ioe);
					}
				catch (Exception e)
					{
						System.out.println("In Exception: " + e);
					}

				socket.close();
			}
	}
