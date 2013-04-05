import java.util.Scanner;


public class PacketProcessor {
	
	
	
	void process(String rec){
		Scanner s = new Scanner(rec);
		
		while(s.hasNext()){
			//Handle Level Pieces
			if(s.next().equals("LID")){
				int id = s.nextInt();
				String objectID = s.next(); //String for loading image + wireframe
				int cx = s.nextInt();
				int cy = s.nextInt();
				
			}
			//Handle Player Objects
			if(s.next().equals("PID")){
				int id = s.nextInt();
				String objectID = s.next(); //String for loading image + wireframe
				int cx = s.nextInt();
				int cy = s.nextInt();
				double velX = s.nextDouble();
				double velY = s.nextDouble();
			}
			
			if(s.next().equals("EID")){
				String eventID = s.next();
				
			}
			
			
			
			
			
//			int numCoords = s.nextInt();
//			Vect[] coords = new Vect[numCoords];
//			s.next();
//			for(int i = 0; i<numCoords; i+=2){
//				coords[i] = new Vect(i, i+1);
//			}
//			s.next();
			//Needs to use data now
		}
		
	}
	
	
	
}
