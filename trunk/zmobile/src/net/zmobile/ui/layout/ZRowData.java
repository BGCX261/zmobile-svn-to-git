package net.zmobile.ui.layout;

import net.zmobile.ge.ZPoint;

public final class ZRowData {

	public ZRowData() {
		width = -1;
		height = -1;
		exclude = false;
	}

	public ZRowData(int i, int j) {
		width = -1;
		height = -1;
		exclude = false;
		width = i;
		height = j;
	}

	public ZRowData(ZPoint point) {
		this(point.x, point.y);
	}

	String getName() {
		String s = getClass().getName();
		int i = s.lastIndexOf('.');
		if (i == -1)
			return s;
		else
			return s.substring(i + 1, s.length());
	}

	public String toString() {
		String s = getName() + " {";
		if (width != -1)
			s = s + "width=" + width + " ";
		if (height != -1)
			s = s + "height=" + height + " ";
		if (exclude)
			s = s + "exclude=" + exclude + " ";
		s = s.trim();
		s = s + "}";
		return s;
	}

	public int width;
	public int height;
	public boolean exclude;
}