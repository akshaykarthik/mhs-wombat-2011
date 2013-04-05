
public class LevelPiece {
	private int ID = -1;
	private Wireframe wire;
	
	
	
	public LevelPiece(int id, Wireframe a){
		this.ID = id;
		wire = a;
		
	}
	
	public void translate(double x, double y){
		wire.translate(x, y);
	}
	public void translateTo(double x, double y){
		wire.translateTo(x, y);
	}
	
	
	
	
	
}
