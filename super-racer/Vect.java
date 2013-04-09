public class Vect
	{
		double	x;
		double	y;

		public Vect(double inx, double iny)
			{
				y = iny;
				x = inx;
			}
		public String toString()
			{
				return "[ "+x + ", " + y+" ]";
			}
		public double dotProduct(Vect v)
			{
				return x * v.x + y * v.y;
			}
		public double velocity()
			{
				return Math.sqrt((x * x) + (y * y));
			}
		public Vect add(Vect v)
			{
				return new Vect(x + v.x, y + v.y);
			}
		public Vect sub(Vect v)
			{
				return new Vect(x - v.x, y - v.y);
			}
		public double distance(Vect v)
			{
				return Math.sqrt(((x - v.x) * (x - v.x)) + ((y - v.y) * (y - v.y)));
			}
	}
