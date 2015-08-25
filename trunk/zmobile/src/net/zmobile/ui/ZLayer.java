package net.zmobile.ui;

import javax.microedition.lcdui.Graphics;

public class ZLayer extends ZContainer {

	public void draw(Graphics g) {
		g.setColor(0x0);
		g.translate(this.translateX, this.translateY);
		drawRect(g, _bodyRect,0);
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).redraw(g);
		}
		g.translate(-this.translateX, -this.translateY);
		if (isScroll()) {
			drawScroll(g);
		}
	}

}
