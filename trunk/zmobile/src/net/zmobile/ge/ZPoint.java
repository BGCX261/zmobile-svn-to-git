package net.zmobile.ge;
/**
 * 
 * @author zhurx
 *
 */
public final class ZPoint {

	public ZPoint(int i, int j) {
		x = i;
		y = j;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ZPoint))
			return false;
		ZPoint point = (ZPoint) obj;
		return point.x == x && point.y == y;
	}

	public int hashCode() {
		return x ^ y;
	}

	public String toString() {
		return "Point {" + x + ", " + y + "}";
	}

	public int x;
	public int y;
	static final long serialVersionUID = 3257002163938146354L;
}
