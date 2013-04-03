import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Wireframe {
	Vect[] coords;
	int numCoords;
	double xCenter, yCenter, ang;

	Vect axis = new Vect(0, 0);
	double min, max, mina, maxa, minb, maxb;

	CollisionInfo info = new CollisionInfo();

	public Wireframe(Vect[] coordz, int num) {
		coords = new Vect[num];
		coords = coordz;
		numCoords = num;
		findCenter();
		ang = 0;
	}

	public String toString() {
		String r = numCoords + " [ ";
		for (int i = 0; i < numCoords; i++) {
			r += coords[i].toString() + " ";
		}
		r += " ]";
		return r;
	}

	public Wireframe getNew() {
		Vect[] c = new Vect[numCoords];
		for (int i = 0; i < numCoords; i++) {
			c[i] = new Vect(0 + coords[i].x, 0 + coords[i].y);
		}
		return new Wireframe(c, numCoords);
	}

	public Vect getCenter() {
		findCenter();
		return new Vect(xCenter, yCenter);
	}

	public void findCenter() {
		// double area = 0;
		// int xSum = 0, ySum = 0;
		// // find area
		// for (int i = 0; i < numCoords - 1; i++)
		// {
		// area += coords[i].x * coords[i + 1].y - coords[i + 1].x *
		// coords[i].y;
		// xSum += (coords[i].x + coords[i + 1].x) * (coords[i].x * coords[i +
		// 1].y - coords[i + 1].x * coords[i].y);
		// ySum += (coords[i].y + coords[i + 1].y) * (coords[i].x * coords[i +
		// 1].y - coords[i + 1].x * coords[i].y);
		// }
		// area += coords[numCoords - 1].x * coords[0].y - coords[0].x *
		// coords[numCoords - 1].y;
		// xSum += (coords[numCoords - 1].x + coords[0].x) * (coords[numCoords -
		// 1].x * coords[0].y - coords[0].x * coords[numCoords - 1].y);
		// ySum += (coords[numCoords - 1].y + coords[0].y) * (coords[numCoords -
		// 1].x * coords[0].y - coords[0].x * coords[numCoords - 1].y);
		// area = area / 2;
		// System.out.println(xSum + " " + area);
		// xCenter = (int) (xSum / (6 * area));
		// yCenter = (int) (ySum / (6 * area));
		int xSum = 0;
		int ySum = 0;
		for (int i = 0; i < numCoords; i++) {
			xSum += coords[i].x;
			ySum += coords[i].y;
		}
		xCenter = xSum / numCoords;
		yCenter = ySum / numCoords;
	}

	public void rotate(double rot) {
		for (int i = 0; i < numCoords; i++) {
			double Xo = coords[i].x; // temp X location for use in y
										// coords modification
			coords[i].x = xCenter
					+ ((Xo - xCenter) * Math.cos(rot) - (coords[i].y - yCenter)
							* Math.sin(rot));
			coords[i].y = yCenter
					+ ((Xo - xCenter) * Math.sin(rot) + (coords[i].y - yCenter)
							* Math.cos(rot));
		}
	}

	public void rotateToward(double inx, double iny) {
		double currentAng = 0 + ang;
		ang = Math.atan2(iny - yCenter, inx - xCenter);
		rotate(ang - currentAng);

	}

	public void rotateToward(int inx, int iny) {
		rotateToward((double) inx, (double) iny);
	}

	public void translate(double inx, double iny) {
		for (int i = 0; i < numCoords; i++) {
			coords[i].x += inx;
			coords[i].y += iny;
		}
		xCenter += inx;
		yCenter += iny;
		// findCenter(); instead of the lines above
	}

	public void translateTo(double inx, double iny) {
		for (int i = 0; i < numCoords; i++) {
			coords[i].x = coords[i].x - xCenter + inx;
			coords[i].y = coords[i].y - yCenter + iny;
		}
		xCenter = inx;
		yCenter = iny;
		// findCenter(); instead of the lines above
	}

	public void render(Graphics2D g, double origX, double origY, Color c) {
		g.setColor(c);
		for (int i = 0; i < numCoords - 1; i++) {
			g.drawLine((int) (coords[i].x - origX),
					(int) (coords[i].y - origY),
					(int) (coords[i + 1].x - origX),
					(int) (coords[i + 1].y - origY));
			g.fillOval((int) (coords[i].x - origX - 2), (int) (coords[i].y
					- origY - 2), 4, 4);
		}
		// the below line is needed to close the shape
		g.fillOval((int) (coords[numCoords - 1].x - origX - 2),
				(int) (coords[numCoords - 1].y - origY - 2), 4, 4);

		g.drawLine((int) (coords[0].x - origX), (int) (coords[0].y - origY),
				(int) (coords[numCoords - 1].x - origX),
				(int) (coords[numCoords - 1].y - origY));
		g.drawOval((int) (xCenter - 5 - origX), (int) (yCenter - 5 - origY),
				10, 10);
	}

	public boolean collide(Wireframe e) {
		// test separation axes of current n-gon
		for (int j = numCoords - 1, i = 0; i < numCoords; j = i, i++) {
			Vect v0 = coords[j];
			Vect v1 = coords[i];

			Vect edge = new Vect(0, 0);
			edge.x = v1.x - v0.x; // edge
			edge.y = v1.y - v0.y; // edge

			axis = new Vect(-edge.y, edge.x); // Separate axis is
												// perpendicular to
												// the edge

			if (separatedByAxis(axis, e))
				return false;
		}

		// test separation axes of other n-gon
		for (int j = e.numCoords - 1, i = 0; i < e.numCoords; j = i, i++) {
			Vect v0 = e.coords[j];
			Vect v1 = e.coords[i];

			Vect edge2 = new Vect(0, 0);
			edge2.x = v1.x - v0.x; // edge
			edge2.y = v1.y - v0.y; // edge

			axis = new Vect(-edge2.y, edge2.x); // Separate axis
												// is
												// perpendicular
												// to the edge

			if (separatedByAxis(axis, e))
				return false;
		}
		return true;
	}

	// public boolean collide(Entity e)
	// {
	// for (int i = 0; i < e.numWF; i++)
	// if (this.collide(e.wireframes[i]))
	// return true;
	// return false;
	// }
	public boolean separatedByAxis(Vect axis3, Wireframe e) {
		calculateInterval(axis3);
		mina = min;
		maxa = max;
		e.calculateInterval(axis3);
		minb = e.min;
		maxb = e.max;

		double d0 = maxb - mina;
		double d1 = minb - maxa;

		// no overlap detected
		if (d0 < 0.0 || d1 > 0.0) {
			return true;
		}

		// find out if overlap on the right or left of the ngon.
		double overlap = (d0 < -d1) ? d0 : d1;

		// the axis length squared
		double axis_length_squared = axis3.dotProduct(axis3);
		assert (axis_length_squared > 0.00001);

		// the mtd Coord for that axis
		Vect sep = new Vect(0, 0);
		sep.x = axis3.x * (overlap / axis_length_squared);
		sep.y = axis3.y * (overlap / axis_length_squared);

		// the mtd Coord length squared.
		double sep_length_squared = sep.dotProduct(sep);

		// if that Coord is smaller than the computed mtd
		// use that Coord as our current mtd.
		if (sep_length_squared < info.LengthSquared || info.LengthSquared < 0.0) {
			info.LengthSquared = sep_length_squared;
			info.mtd = sep;
		}
		return false;

		// return intervalsSeparated(mina, maxa, minb, maxb);
	}

	public void calculateInterval(Vect ax) {
		max = coords[0].dotProduct(ax);
		min = coords[0].dotProduct(ax);

		for (int i = 1; i < numCoords; i++) {
			double d = coords[i].dotProduct(ax);
			if (d < min)
				min = d;
			else if (d > max)
				max = d;
		}
	}

	public CollisionInfo collideInfo(Wireframe e) {

		info.LengthSquared = -1;
		// this.info.overlap=false;

		// test separation axes of current egon
		for (int j = numCoords - 1, i = 0; i < numCoords; j = i, i++) {
			Vect v0 = coords[j];
			Vect v1 = coords[i];

			Vect edge = new Vect(0, 0);
			edge.x = v1.x - v0.x; // edge
			edge.y = v1.y - v0.y; // edge

			this.axis = new Vect(-edge.y, edge.x); // Separate
													// axis is
													// perpendicular
			// to the edge

			if (separatedByAxis(axis, e)) {
				info.overlap = false;
				return info;
			}
		}

		// test separation axes of other egon
		for (int j = e.numCoords - 1, i = 0; i < e.numCoords; j = i, i++) {
			Vect v0 = e.coords[j];
			Vect v1 = e.coords[i];

			Vect edge2 = new Vect(0, 0);
			edge2.x = v1.x - v0.x; // edge
			edge2.y = v1.y - v0.y; // edge

			this.axis = new Vect(-edge2.y, edge2.x); // Separate
														// axis is
														// perpendicular
			// to the edge

			if (separatedByAxis(axis, e)) {
				info.overlap = false;
				return info;
			}
		}
		info.overlap = true;
		return info;
	}

	public ArrayList<Line2D.Double> getLineWireFrame() {
		ArrayList<Line2D.Double> ret = new ArrayList<Line2D.Double>();

		for (int i = 0; i < numCoords - 1; i++) {
			ret.add(new Line2D.Double(coords[i].x, coords[i].y,
					coords[i + 1].x, coords[i + 1].y));
		}
		ret.add(new Line2D.Double(coords[numCoords - 1].x,
				coords[numCoords - 1].y, coords[0].x, coords[0].y));

		return ret;
	}

}

class CollisionInfo {
	Vect mtd;
	boolean overlap;
	double LengthSquared;
}
