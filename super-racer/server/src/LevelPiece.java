
public class LevelPiece {
	private int ID = -1;
	private double x, y;
	
	public LevelPiece(int id, double x, double y){
		this.ID = id;
		this.x = x;
		this.y = y;
		
	}
	
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	
	
}
