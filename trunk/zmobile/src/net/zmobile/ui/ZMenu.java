package net.zmobile.ui;

import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ui.layout.ZFillLayout;

public class ZMenu extends ZContainer {

	public final static byte LEFT_KEY = 0;
	public final static byte RIGTH_KEY = 1;
	byte _type = LEFT_KEY;

	Vector leftVector = new Vector();
	Vector rightVector = new Vector();
	public ZMenu() {
		this._bodyRect.x = 0;
		this._bodyRect.y = 0;
		this._bodyRect.width = 100;
		this._bodyRect.height = Font.getDefaultFont().getHeight();
		this.setLayout(new ZFillLayout(ZMobile.FILL_VERTICAL));
	}

	public void draw(Graphics g) {
		this._bodyRect.height = _components.size()*Font.getDefaultFont().getHeight();
		setClip(g,_bodyRect,1);
		g.setColor(0xffffff);
		fillRect(g,_bodyRect,0);
		super.draw(g);
		g.setColor(0x0);
		
		setClip(g,_bodyRect,1);
		drawRect(g,_bodyRect,0);
		g.drawLine(_bodyRect.x, _bodyRect.y, _bodyRect.width, _bodyRect.height);
		this.setFocus(true);
	}

	public void append(String title, byte type) {
		if(LEFT_KEY==type){
			leftVector.addElement((new ZMenuItem(title, type)));
		}else{
			rightVector.addElement((new ZMenuItem(title, type)));			
		}
		this._bodyRect.height += Font.getDefaultFont().getHeight();
		resize();
	}

	public void show(byte type) {
		_type = type;
		resize();
		ZFormCanvas.push(this);
	}

	class ZMenuItem extends ZComponent {
		String title = "";
		public byte _type = 0;

		public ZMenuItem(String title, byte type) {
			this.title = title;
			_type = type;
		}

		public void draw(Graphics g) {
			if (isFoucus()) {
				g.setColor(0, 0, 255);
				fillRect(g,_bodyRect,0);
				g.setColor(255, 255, 255);
			}
			drawString(g,this._bodyRect,title);
		}

		public void firePointRelease(ZPoint point) {
			super.firePointRelease(point);
			if(_bodyRect.contains(point)){
				ZFormCanvas.pop();
			}
			
		}

		public void drawStatus(Graphics g) {

		}

		public ZPoint computeSize(int w, int h) {
			return null;
		}

	}

	public void resize() {
		if(LEFT_KEY==_type){
			this._components = leftVector;
		}else{
			this._components = rightVector;			
		}
		_bodyRect.height = _components.size()*Font.getDefaultFont().getHeight();
		super.resize();
	}

}
