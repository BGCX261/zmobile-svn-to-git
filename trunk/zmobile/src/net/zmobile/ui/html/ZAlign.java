package net.zmobile.ui.html;

import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ui.ZComponent;

public class ZAlign extends ZComponent {

	public ZPoint computeSize(int w, int h) {
		return null;
	}

	public void draw(Graphics g) {

	}

	public void drawStatus(Graphics g) {

	}
	private int _align = ZMobile.LEFT;
	public ZAlign(int align){
		_align = align;
		this._isCanFoucus = false;
	}
	public int getAlign(){
		return _align;
	}

}
