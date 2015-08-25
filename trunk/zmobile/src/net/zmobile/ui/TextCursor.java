package net.zmobile.ui;

import javax.microedition.lcdui.Graphics;

public class TextCursor {

	public TextCursor(int index) {
		this.index = index;
		option = 0;
		visible = true;
	}

	public TextCursor(TextCursor tc) {
		this(0);
		if (tc != null) {
			x = tc.x;
			y = tc.y;
			option = tc.option;
			index = tc.index;
			visible = tc.visible;
		}
	}

	public void paint(Graphics g) {
		int stroke = g.getStrokeStyle();
		Graphics _tmp = g;
		g.setStrokeStyle(0);
		g.drawLine(x - 1, y - height, (x - 1) + (width - 1), y);
		g.setStrokeStyle(stroke);
	}

	public int x;
	public int y;
	public int width;
	public int height;
	public int index;
	public int option;
	public boolean visible;
	public int preferredX;
}
