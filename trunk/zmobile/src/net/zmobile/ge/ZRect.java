package net.zmobile.ge;

import net.zmobile.ZMobile;

/**
 * 
 * @author zhurx
 *
 */
public final class ZRect {

	public ZRect(int i, int j, int k, int l) {
		x = i;
		y = j;
		width = k;
		height = l;
	}

	public void add(ZRect rectangle) {
		if (rectangle == null)
			ZMobile.error(4);
		int i = x >= rectangle.x ? rectangle.x : x;
		int j = y >= rectangle.y ? rectangle.y : y;
		int k = x + width;
		int l = rectangle.x + rectangle.width;
		int i1 = k <= l ? l : k;
		k = y + height;
		l = rectangle.y + rectangle.height;
		int j1 = k <= l ? l : k;
		x = i;
		y = j;
		width = i1 - i;
		height = j1 - j;
	}

	public boolean contains(int i, int j) {
		return i >= x && j >= y && i - x < width && j - y < height;
	}

	public boolean contains(ZPoint point) {
		if (point == null)
			ZMobile.error(4);
		return contains(point.x, point.y);
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ZRect))
			return false;
		ZRect rectangle = (ZRect) obj;
		return rectangle.x == x && rectangle.y == y && rectangle.width == width
				&& rectangle.height == height;
	}

	public int hashCode() {
		return x ^ y ^ width ^ height;
	}

	public void intersect(ZRect rectangle) {
		if (rectangle == null)
			ZMobile.error(4);
		if (this == rectangle) {
			return;
		} else {
			int i = x <= rectangle.x ? rectangle.x : x;
			int j = y <= rectangle.y ? rectangle.y : y;
			int k = x + width;
			int l = rectangle.x + rectangle.width;
			int i1 = k >= l ? l : k;
			k = y + height;
			l = rectangle.y + rectangle.height;
			int j1 = k >= l ? l : k;
			x = i1 >= i ? i : 0;
			y = j1 >= j ? j : 0;
			width = i1 >= i ? i1 - i : 0;
			height = j1 >= j ? j1 - j : 0;
			return;
		}
	}

	public ZRect intersection(ZRect rectangle) {
		if (rectangle == null)
			ZMobile.error(4);
		if (this == rectangle) {
			return new ZRect(x, y, width, height);
		} else {
			int i = x <= rectangle.x ? rectangle.x : x;
			int j = y <= rectangle.y ? rectangle.y : y;
			int k = x + width;
			int l = rectangle.x + rectangle.width;
			int i1 = k >= l ? l : k;
			k = y + height;
			l = rectangle.y + rectangle.height;
			int j1 = k >= l ? l : k;
			return new ZRect(i1 >= i ? i : 0, j1 >= j ? j : 0, i1 >= i ? i1 - i
					: 0, j1 >= j ? j1 - j : 0);
		}
	}

	public boolean intersects(int i, int j, int k, int l) {
		return i < x + width && j < y + height && i + k > x && j + l > y;
	}

	public boolean intersects(ZRect rectangle) {
		if (rectangle == null)
			ZMobile.error(4);
		return rectangle == this
				|| intersects(rectangle.x, rectangle.y, rectangle.width,
						rectangle.height);
	}

	public boolean isEmpty() {
		return width <= 0 || height <= 0;
	}

	public String toString() {
		return "Rectangle {" + x + ", " + y + ", " + width + ", " + height
				+ "}";
	}

	public ZRect union(ZRect rectangle) {
		if (rectangle == null)
			ZMobile.error(4);
		int i = x >= rectangle.x ? rectangle.x : x;
		int j = y >= rectangle.y ? rectangle.y : y;
		int k = x + width;
		int l = rectangle.x + rectangle.width;
		int i1 = k <= l ? l : k;
		k = y + height;
		l = rectangle.y + rectangle.height;
		int j1 = k <= l ? l : k;
		return new ZRect(i, j, i1 - i, j1 - j);
	}

	public int x;
	public int y;
	public int width;
	public int height;
	static final long serialVersionUID = 3256439218279428914L;
}