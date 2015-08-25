package net.zmobile.ui.html;

import javax.microedition.lcdui.Graphics;

import net.zmobile.ge.ZPoint;
import net.zmobile.ui.ZComponent;

public class ZBr extends ZComponent {

	public ZPoint computeSize(int w, int h) {
		return new ZPoint(0,0);
	}

	public void draw(Graphics g) {

	}

	public void drawStatus(Graphics g) {

	}
	public ZBr(){
		this._isCanFoucus = false;
	}

}
